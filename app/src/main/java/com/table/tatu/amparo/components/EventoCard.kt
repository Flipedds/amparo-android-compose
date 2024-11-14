package com.table.tatu.amparo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.models.Evento

@Composable
fun EventoCard(evento: Evento){
    Surface(
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .heightIn(220.dp, 240.dp)
                .width(367.dp)
        ) {
            Image(
                painter = painterResource(id = evento.imagem),
                contentDescription = "evento card",
                modifier = Modifier
                    .width(367.dp)
                    .height(175.3.dp))
            Text(
                text = evento.titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 11.11.dp))
            Box(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = evento.descricao,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(horizontal = 11.11.dp))
            }
        }
    }
}

@Preview
@Composable
private fun EventoCardPreview(){
    EventoCard(
        Evento(
            id = 1,
            titulo = "Não se cale, Denuncie !",
            descricao = LoremIpsum(50).values.joinToString(),
            imagem = R.drawable.ic_amparo_launcher
        )
    )
}