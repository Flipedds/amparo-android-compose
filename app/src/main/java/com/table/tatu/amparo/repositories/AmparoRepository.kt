package com.table.tatu.amparo.repositories

import com.table.tatu.amparo.dtos.AuthLoginResponse
import com.table.tatu.amparo.models.Credentials
import com.table.tatu.amparo.models.Denounce
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.models.User
import com.table.tatu.amparo.models.UserCredential
import com.table.tatu.amparo.models.UserHeader
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface AmparoRepository {
    @GET("post/")
    suspend fun findAllPosts(@Header("Content-Type") contentType: String): List<Post>

    @POST("auth/login")
    suspend fun authenticateUser(
        @Header("Content-Type") contentType: String,
        @Body credentials: Credentials
    ): AuthLoginResponse

    @POST("auth/register")
    suspend fun registerUser(
        @Header("Content-Type") contentType: String,
        @Body credentials: Credentials
    ): AuthLoginResponse

    @GET("user/me")
    suspend fun getUser(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") token: String
    ): User

    @PUT("user/header")
    suspend fun updateHeaderUser(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String,
        @Body userHeader: UserHeader
    ): UserHeader

    @PUT("user/credential")
    suspend fun updateCredentialUser(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String,
        @Body userCredential: UserCredential
    ): UserCredential

    @POST("denounce/")
    suspend fun newDenounce(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String,
        @Body denounce: Denounce
    )
}