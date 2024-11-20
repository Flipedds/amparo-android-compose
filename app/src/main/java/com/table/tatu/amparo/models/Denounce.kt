package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Denounce(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
){}