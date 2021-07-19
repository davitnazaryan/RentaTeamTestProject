package ru.rentateam.testproject.data.remote.retrofit.adapter

import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import ru.rentateam.testproject.data.remote.retrofit.exception.NoConnectionError
import ru.rentateam.testproject.data.remote.retrofit.exception.RetrofitException
import java.io.IOException


internal class FlowCall<S : Any>(
    private val delegate: Call<S>,
    private val retrofit: Retrofit
) : Call<Flow<S>> {

    override fun enqueue(callback: Callback<Flow<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@FlowCall,
                            Response.success(flow {
                                emit(body)
                            })
                        )
                        return
                    }
                } else {
                    if (response.errorBody() != null) {
                        callback.onResponse(
                            this@FlowCall,
                            Response.success(flow {
                                throw asRetrofitException(
                                    retrofit, RetrofitException.httpError(
                                        response, retrofit
                                    )
                                )
                            })
                        )
                        return
                    }
                }

                callback.onResponse(
                    this@FlowCall,
                    Response.success(flow {
                        throw asRetrofitException(
                            retrofit, RetrofitException(
                                "unknownError",
                                null,
                                null,
                                RetrofitException.Kind.UNEXPECTED,
                                null,
                                null,
                                null
                            )
                        )
                    })
                )
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                callback.onResponse(
                    this@FlowCall,
                    Response.success(flow {
                        throw asRetrofitException(
                            retrofit, throwable
                        )
                    })
                )
            }
        })
    }

    private fun asRetrofitException(retrofit: Retrofit, throwable: Throwable): Throwable =
        when (throwable) {
            is HttpException -> {
                val response = throwable.response()
                RetrofitException.httpError(response, retrofit)
            }
            is NoConnectionError -> RetrofitException.noConnectionError(throwable)
            is IOException -> RetrofitException.networkError(throwable)
            is JsonDataException -> {
                throwable
            }
            else -> throwable
        }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = FlowCall(delegate.clone(), retrofit)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Flow<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}