package com.mrmobo.conduit.data

import com.mrmobo.api.ConduitClient
import com.mrmobo.api.models.entities.LoginData
import com.mrmobo.api.models.entities.SignupData
import com.mrmobo.api.models.entities.User
import com.mrmobo.api.models.entities.UserUpdateData
import com.mrmobo.api.models.requests.LoginRequest
import com.mrmobo.api.models.requests.SignupRequest
import com.mrmobo.api.models.requests.UserUpdateRequest
import com.mrmobo.api.models.responses.UserResponse

object UserRepo {
    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun login(email: String, password: String): User? {
        val response = api.loginUser(LoginRequest(LoginData(email, password)))

        //sharedprefernces
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun signup(username: String, email: String, password: String): User? {
        val response = api.signupUser(SignupRequest(SignupData(
            email, password, username
        )))

        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user
    }

    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
       return authAPI.getCurrentUser().body()?.user
    }

    suspend fun updateUser(
        bio: String?,
        username: String?,
        image: String?,
        email: String?,
        password: String?
    ) : User? {
        val response = authAPI.updateCurrentUser(UserUpdateRequest(UserUpdateData(
            bio, email, image, username, password
        )))

        return response.body()?.user
    }


    suspend fun getUserProfile() = authAPI.getCurrentUser().body()?.user
}