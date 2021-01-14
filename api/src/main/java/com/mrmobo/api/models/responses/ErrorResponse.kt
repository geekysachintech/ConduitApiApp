package com.mrmobo.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.mrmobo.api.models.entities.Errors

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val errors: Errors
)