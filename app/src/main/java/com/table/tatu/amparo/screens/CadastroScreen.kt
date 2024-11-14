package com.table.tatu.amparo.screens

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.table.tatu.amparo.R
import com.table.tatu.amparo.components.CadastroForm
import com.table.tatu.amparo.ui.theme.amparoDefaultColor

@Composable
fun CadastroScreen(
    onNavigateToLogin: () -> Unit
) {
    var location by remember { mutableStateOf<Location?>(null) }
    var permissionGranted by remember { mutableStateOf(false) }

    // Solicita permissão para acesso à localização
    val requestPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        permissionGranted = isGranted
    }

    val context = LocalContext.current

    // Verifica as permissões de localização
    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            permissionGranted = true
        }
    }

    if (permissionGranted) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        LaunchedEffect(permissionGranted) {
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener { currentLocation ->
                    location = currentLocation
                }
            } catch (e: SecurityException) {
                Log.i("DEBUG", "Erro durante acesso a localização")
                location = null
            }
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
        CadastroForm(onNavigateToLogin = onNavigateToLogin, location = location)
    }
}


@Preview
@Composable
private fun CadastroScreenPreview() {
    CadastroScreen(onNavigateToLogin = {})
}