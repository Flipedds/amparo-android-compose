package com.table.tatu.amparo.services

import com.table.tatu.amparo.dtos.AuthLoginResponse
import com.table.tatu.amparo.models.Credentials
import com.table.tatu.amparo.models.Denounce
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.repositories.AmparoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AmparoService(
    private val amparoRepository: AmparoRepository
) {
    suspend fun getAllPosts(): Flow<List<Post>> = flow {
        while (true) {
            emit(amparoRepository.findAllPosts("application/json"))
            delay(5000)
        }
    }

    suspend fun authenticateUser(login: String, senha: String): AuthLoginResponse {
        return amparoRepository.authenticateUser(
            "application/json", Credentials(login, senha)
        )
    }

    suspend fun newDenounce(token: String, name: String, description: String){
        amparoRepository.newDenounce(token, "application/json", Denounce(name, description))
    }
}