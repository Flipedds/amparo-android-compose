package com.table.tatu.amparo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.table.tatu.amparo.R
import com.table.tatu.amparo.components.EventoSection
import com.table.tatu.amparo.components.MenuBar
import com.table.tatu.amparo.models.Evento
import com.table.tatu.amparo.samples.sampleEventos
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.theme.amparoHomeSecondaryTextColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily

@Composable
fun HomeScreen(navController: NavHostController, eventos: List<Evento>) {
    Scaffold(
        bottomBar = {
            MenuBar()
        },
        topBar = {
            Spacer(modifier = Modifier
                .height(39.dp)
                .fillMaxWidth()
                .background(amparoButtonColor))
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(amparoDefaultColor)) {
            Image(
                painterResource(id = R.drawable.ic_amparo_launcher),
                contentDescription = "Logo Amparo",
                modifier = Modifier
                    .width(302.dp)
                    .height(114.dp)
                    .offset(x = 0.dp, y = -(10).dp)
                    .align(Alignment.CenterHorizontally))
            Text(text = "Estamos aqui para lhe ajudar",
                fontFamily = grandstanderFontFamily,
                fontSize = 16.sp,
                color = amparoHomeTextColor,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = -(25).dp))
            Text(text = "peça ajuda",
                fontFamily = grandstanderFontFamily,
                fontSize = 24.sp,
                color = amparoHomeTextColor,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = -(20).dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "busque ",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 24.sp,
                    color = amparoHomeTextColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .offset(y = -(20).dp))
                Text(text = "Amparo",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 24.sp,
                    color = amparoHomeSecondaryTextColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .offset(y = -(20).dp))
            }
            EventoSection(eventos = eventos)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController, sampleEventos)
}