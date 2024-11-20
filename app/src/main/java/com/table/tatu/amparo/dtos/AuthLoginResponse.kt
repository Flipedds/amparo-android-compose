package com.table.tatu.amparo.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class AuthLoginResponse(@SerializedName("accessToken") val accessToken: String)