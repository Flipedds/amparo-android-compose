package com.table.tatu.amparo.services

import com.table.tatu.amparo.dtos.AuthLoginResponse
import com.table.tatu.amparo.models.Credentials
import com.table.tatu.amparo.models.Denounce
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.models.SupportNetwork
import com.table.tatu.amparo.models.User
import com.table.tatu.amparo.models.UserCredential
import com.table.tatu.amparo.models.UserHeader
import com.table.tatu.amparo.repositories.AmparoRepository

class AmparoService(
    private val amparoRepository: AmparoRepository
) {
    suspend fun getAllPosts(): List<Post> {
        return amparoRepository.findAllPosts("application/json")
    }

    suspend fun getUserSupportNetwork(token: String): MutableList<SupportNetwork> {
        return amparoRepository.getUserSupportNetwork(token, "application/json")
    }

    suspend fun updateUserSupportNetwork(token: String, userSupportNetwork: MutableList<SupportNetwork>): MutableList<SupportNetwork> {
        return amparoRepository.updateUserSupportNetwork(token, "application/json", userSupportNetwork)
    }

    suspend fun authenticateUser(login: String, senha: String): AuthLoginResponse {
        return amparoRepository.authenticateUser(
            "application/json", Credentials(login, senha)
        )
    }

    suspend fun registerUser(email: String, senha: String): AuthLoginResponse {
        return amparoRepository.registerUser(
            "application/json", Credentials(email, senha)
        )
    }

    suspend fun getUser(token: String): User {
        return amparoRepository.getUser("application/json", token)
    }

    suspend fun updateHeaderUser(token: String, userHeader: UserHeader): UserHeader {
        return amparoRepository.updateHeaderUser(token, "application/json", userHeader)
    }

    suspend fun updateCredentialUser(token: String, userHeader: UserCredential): UserCredential {
        return amparoRepository.updateCredentialUser(token, "application/json", userHeader)
    }

    suspend fun newDenounce(token: String, name: String, description: String) {
        amparoRepository.newDenounce(token, "application/json", Denounce(name, description))
    }
}