package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserHeader(
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("address") var address: Address? = null,
    @SerializedName("age") var age: String? = null
)

@Serializable
data class UserCredential(
    @SerializedName("cpf") var cpf: String? = null,
    @SerializedName("phoneNumber") var phoneNumber: String? = null,
    @SerializedName("email") val email: String
)

@Serializable
class User(
    @SerializedName("id") val id: String,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("phoneNumber") var phoneNumber: String? = null,
    @SerializedName("age") var age: String? = null,
    @SerializedName("cpf") var cpf: String? = null,
    @SerializedName("address") var address: Address? = null
) {
    fun toUserHeader(): UserHeader {
        return UserHeader(this.name, this.description, this.address, this.age)
    }

    fun toUserCredential(): UserCredential {
        return UserCredential(this.cpf, this.phoneNumber, this.email)
    }
}