package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Post(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("creationDate") val creationDate: String
) {}