package com.mrmobo.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.mrmobo.api.models.entities.LoginData

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "user")
    val user: LoginData
)