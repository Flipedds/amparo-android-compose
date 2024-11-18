package com.table.tatu.amparo.samples

import com.google.android.gms.maps.model.LatLng
import com.table.tatu.amparo.models.Post

val samplePosts = listOf(
    Post(
        id = "98493489",
        title = "Não se cale, Denuncie !",
        creationDate = "11/11/11"
    ),
    Post(
        id = "439i40390",
        title = "Empoderamento | RECIFE 30 NOV",
        creationDate = "11/11/11"
    ),
    Post(
        id = "gjrigjrigr84",
        title = "Não se cale, Denuncie !",
        creationDate = "11/11/11"
    )
)

data class Locate(
    val name: String,
    val description: String,
    val coordinates: LatLng
)

val markers = listOf(
    Locate(
        "Casa Empodera Mulher",
        "Conselho Municipal da Mulher",
        LatLng(-8.0536, -34.8884)),
    Locate(
        "Delegacia da Mulher",
        "Departamento de polícia",
        LatLng(-8.0495, -34.8828)),
)