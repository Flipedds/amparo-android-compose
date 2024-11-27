package com.table.tatu.amparo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.ui.animations.LoadingAnimation
import com.table.tatu.amparo.ui.components.CadastroForm
import com.table.tatu.amparo.ui.states.CadastroFormState
import com.table.tatu.amparo.ui.states.UiState
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.viewmodels.CadastroScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CadastroScreen(
    cadastroScreenViewModel: CadastroScreenViewModel,
    onNavigateToLogin: () -> Unit,
    onCreateNewUser: (CadastroFormState) -> Unit
) {
    val uiState by cadastroScreenViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(cadastroScreenViewModel.toastEvent) {
        cadastroScreenViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

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
        when (uiState) {
            UiState.Default -> CadastroForm(
                onNavigateToLogin = onNavigateToLogin,
                onCreateNewUser = onCreateNewUser)
            UiState.Loading -> LoadingAnimation(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            else -> TODO()
        }
    }
}


@Preview
@Composable
private fun CadastroScreenPreview() {
    CadastroScreen(
        onNavigateToLogin = {},
        onCreateNewUser = { _: CadastroFormState -> },
        cadastroScreenViewModel = koinViewModel<CadastroScreenViewModel>())
}