package com.table.tatu.amparo.repositories

import com.table.tatu.amparo.models.Post
import retrofit2.http.GET
import retrofit2.http.Header

interface AmparoRepository {
    @GET("post/")
    suspend fun findAllPosts(@Header("Content-Type") contentType: String): List<Post>
}