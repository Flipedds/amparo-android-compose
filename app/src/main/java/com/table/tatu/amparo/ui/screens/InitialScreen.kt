package com.table.tatu.amparo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.ui.theme.amparoDefaultColor

@Composable
fun InitialScreen(onNavigateToLogin: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
            .clickable { onNavigateToLogin() }) {
        Image(painterResource(id = R.drawable.ic_amparo_launcher),
            contentDescription = "Logo Amparo",
            modifier = Modifier
                .width(537.dp)
                .height(289.dp)
                .offset(x=0.dp, y=300.dp))
    }
}

@Preview
@Composable
private fun InitialScreenPreview(){
    InitialScreen(onNavigateToLogin = {})
}
