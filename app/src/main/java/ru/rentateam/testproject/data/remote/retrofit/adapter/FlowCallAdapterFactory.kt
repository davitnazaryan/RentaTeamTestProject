package ru.rentateam.testproject.data.remote.retrofit.adapter

import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (returnType !is ParameterizedType) return null
        val responseType = getParameterUpperBound(0, returnType)
        if (responseType !is ParameterizedType) return null
        if (getRawType(responseType) != Flow::class.java) {
            return null
        }
        val successBodyType = getParameterUpperBound(0, responseType)
        return FlowAdapter<Any>(successBodyType, retrofit)
    }
}