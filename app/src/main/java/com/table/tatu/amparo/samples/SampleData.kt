package com.table.tatu.amparo.samples

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.table.tatu.amparo.R
import com.table.tatu.amparo.models.Evento

val sampleEventos = listOf(
    Evento(
        id = 1,
        titulo = "Não se cale, Denuncie !",
        descricao = LoremIpsum(50).values.joinToString(),
        imagem = R.drawable.ic_amparo_launcher
    ),
    Evento(
        id = 1,
        titulo = "Empoderamento | RECIFE 30 NOV",
        descricao = LoremIpsum(50).values.joinToString(),
        imagem = R.drawable.ic_amparo_launcher
    ),
    Evento(
        id = 1,
        titulo = "Não se cale, Denuncie !",
        descricao = LoremIpsum(50).values.joinToString(),
        imagem = R.drawable.ic_amparo_launcher
    )
)