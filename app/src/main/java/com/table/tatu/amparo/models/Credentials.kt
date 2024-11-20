package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Credentials(
    @SerializedName("credential") val credential: String,
    @SerializedName("password") val password: String
) {}