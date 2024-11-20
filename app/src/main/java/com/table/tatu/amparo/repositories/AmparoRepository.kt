package com.table.tatu.amparo.repositories

import com.table.tatu.amparo.dtos.AuthLoginResponse
import com.table.tatu.amparo.models.Credentials
import com.table.tatu.amparo.models.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AmparoRepository {
    @GET("post/")
    suspend fun findAllPosts(@Header("Content-Type") contentType: String): List<Post>

    @POST("auth/login")
    suspend fun authenticateUser(@Header("Content-Type") contentType: String, @Body credentials: Credentials): AuthLoginResponse
}