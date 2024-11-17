package com.table.tatu.amparo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.ui.components.LoginForm
import com.table.tatu.amparo.ui.theme.amparoDefaultColor

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToCadastro: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
    ) {
        Image(
            painterResource(id = R.drawable.ic_amparo_launcher),
            contentDescription = "Logo Amparo",
            modifier = Modifier
                .width(370.dp)
                .height(210.dp)
                .offset(x = 0.dp, y = 30.dp)
                .align(Alignment.CenterHorizontally)
        )
        LoginForm(
            onNavigateToHome = onNavigateToHome,
            onNavigateToCadastro = onNavigateToCadastro
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(onNavigateToHome = {}, onNavigateToCadastro = {})
}

