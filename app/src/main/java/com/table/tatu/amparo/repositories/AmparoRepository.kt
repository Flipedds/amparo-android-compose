package com.table.tatu.amparo.repositories

import com.table.tatu.amparo.models.Post
import retrofit2.http.GET

interface AmparoRepository {
    @GET("post/")
    suspend fun findAllPosts(): List<Post>
}