package com.table.tatu.amparo.ui.screens

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.ui.animations.LoadingAnimation
import com.table.tatu.amparo.ui.components.DenounceForm
import com.table.tatu.amparo.ui.states.UiState
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily
import com.table.tatu.amparo.ui.viewmodels.DenounceScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DenounceScreen() {
    val denounceScreenViewModel = koinViewModel<DenounceScreenViewModel>()
    val permissionGranted by denounceScreenViewModel.permissionGranted.collectAsState()
    val uiState by denounceScreenViewModel.uiState.collectAsState()
    val location by denounceScreenViewModel.location.collectAsState()
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        denounceScreenViewModel.checkPermission()
        if (isGranted) {
            denounceScreenViewModel.fetchLocation()
        }
    }

    LaunchedEffect(Unit) {
        if (!permissionGranted) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            denounceScreenViewModel.fetchLocation()
        }
    }

    LaunchedEffect(denounceScreenViewModel.toastEvent) {
        denounceScreenViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    Column {
        Column(
            Modifier
                .fillMaxSize()
                .background(amparoDefaultColor)
                .padding(horizontal = 31.5.dp, vertical = 30.dp)
        ) {
            Text(
                text = "Denunciar",
                fontFamily = grandstanderFontFamily,
                fontSize = 25.sp,
                color = amparoHomeTextColor,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 20.dp)
            )
            when (uiState) {
                UiState.Default -> DenounceForm(
                    location = location,
                    onCreateDenounce = { name: String, description: String, onClearForm: () -> Unit  ->
                        denounceScreenViewModel.createNewDenounce(name, description, onClearForm)
                    })

                UiState.Loading -> LoadingAnimation(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                else -> TODO()
            }
        }
    }
}

@Preview
@Composable
private fun DenounceScreenPreview() {
    DenounceScreen()
}