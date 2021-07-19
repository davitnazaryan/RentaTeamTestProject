package ru.rentateam.testproject.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Envelop<out T>(
    @Json(name = "data")
    val data: T?,
    @Json(name = "error")
    val error: Error?,
)

@JsonClass(generateAdapter = true)
data class Error(
    @field:Json(name = "statusExt")
    val statusExt: Long,
    @field:Json(name = "message")
    val message: String
)