package com.table.tatu.amparo.services

import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.repositories.AmparoRepository

class AmparoService(
    private val amparoRepository: AmparoRepository
) {
    suspend fun getAllPosts(): List<Post> {
        return amparoRepository.findAllPosts()
    }
}