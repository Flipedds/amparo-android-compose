package com.table.tatu.amparo.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
class Evento(
    val id: Long,
    val titulo: String,
    val descricao: String,
    @DrawableRes val imagem: Int
) {
}