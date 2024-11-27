package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class User(
    @SerializedName("id") val id: String,
    @SerializedName("name") var name: String?,
    @SerializedName("email") val email: String,
    @SerializedName("phoneNumber") var phoneNumber: String? = null,
    @SerializedName("roles") var roles: List<String>? = null,
    @SerializedName("age") var age: String? = null,
    @SerializedName("cpf") var cpf: String?,
    @SerializedName("address") var address: Address? = null,
    @SerializedName("supportNetwork") var supportNetwork: List<SupportNetwork>? = null
) {}