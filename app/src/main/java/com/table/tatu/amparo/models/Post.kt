package com.table.tatu.amparo.models

import kotlinx.serialization.Serializable

@Serializable
class Post(
    val id: String,
    val title: String,
    val creationDate: String
) {}