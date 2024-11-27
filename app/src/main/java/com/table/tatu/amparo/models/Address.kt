package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Address(
    @SerializedName("state") val state: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("street") val street: String? = null,
    @SerializedName("complement") val complement: String? = null,
) {}