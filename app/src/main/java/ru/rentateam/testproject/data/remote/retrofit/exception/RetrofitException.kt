package ru.rentateam.testproject.data.remote.retrofit.exception

import retrofit2.Response
import retrofit2.Retrofit
import ru.rentateam.testproject.data.remote.model.Error
import java.io.IOException


class RetrofitException(
    message: String?,
    exception: Throwable?,
    /** Response object containing status code, headers, body, etc.  */
    val response: Response<*>?,
    /** The event kind which triggered this error.  */
    val kind: Kind?,
    /** The Retrofit this request was executed on  */
    val retrofit: Retrofit?,
    var apiErrorMsg: String? = null,
    var apiErrorStatus: Long? = null
): RuntimeException(message, exception) {

    /** Identifies the event kind which triggered a [RetrofitException].  */
    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        /** There is no active internet connection */
        NO_CONNECTION,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.

     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? = response?.errorBody()?.let {
        retrofit?.responseBodyConverter<T>(type, emptyArray<Annotation>())?.convert(it)
    }

    companion object {
        fun httpError(response: Response<*>?, retrofit: Retrofit): RetrofitException {
            return RetrofitException(
                null,
                null,
                response,
                Kind.HTTP,
                retrofit
            ).apply {
                val error = getErrorBodyAs(Error::class.java)
                apiErrorMsg = error?.message
                apiErrorStatus = error?.statusExt
            }
        }

        fun noConnectionError(exception: NoConnectionError): RetrofitException {
            return RetrofitException(
                exception.message,
                exception,
                null,
                Kind.NO_CONNECTION,
                null
            )
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(
                exception.message,
                exception,
                null,
                Kind.NETWORK,
                null
            )
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                exception.message,
                exception,
                null,
                Kind.UNEXPECTED,
                null
            )
        }
    }
}