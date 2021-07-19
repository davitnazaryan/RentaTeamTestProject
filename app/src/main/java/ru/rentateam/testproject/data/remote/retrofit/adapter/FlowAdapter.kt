package ru.rentateam.testproject.data.remote.retrofit.adapter

import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class FlowAdapter<S : Any>(
    private val successType: Type,
    private val retrofit: Retrofit
) : CallAdapter<S, Call<Flow<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Flow<S>> {
        return FlowCall(call, retrofit)
    }
}