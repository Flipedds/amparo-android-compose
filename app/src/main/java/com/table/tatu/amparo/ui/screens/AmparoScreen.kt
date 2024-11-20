package com.table.tatu.amparo.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.table.tatu.amparo.samples.markers
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoMenuColor
import com.table.tatu.amparo.ui.viewmodels.AmparoScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AmparoScreen() {
    val amparoScreenViewModel = koinViewModel<AmparoScreenViewModel>()
    val permissionGranted by amparoScreenViewModel.permissionGranted.collectAsState()
    val location by amparoScreenViewModel.location.collectAsState()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        amparoScreenViewModel.checkPermission()
        if (isGranted) {
            amparoScreenViewModel.fetchLocation()
        }
    }

    LaunchedEffect(Unit) {
        if (!permissionGranted) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            amparoScreenViewModel.fetchLocation()
        }
    }

    // Mapa
    location?.let {
        val userCoordinates = LatLng(location!!.latitude, location!!.longitude)
        val userCoordinatesMarker = rememberMarkerState(position = userCoordinates)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(userCoordinates, 10f)
        }
        var mapType by remember {
            mutableStateOf(MapType.NORMAL)
        }

        Column(Modifier.background(amparoMenuColor)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { mapType = MapType.NORMAL },
                    colors = ButtonColors(
                        containerColor = amparoButtonColor,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text("Normal")
                }
                Button(
                    onClick = { mapType = MapType.SATELLITE },
                    colors = ButtonColors(
                        containerColor = amparoButtonColor,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text("Satélite")
                }
                Button(
                    onClick = { mapType = MapType.HYBRID },
                    colors = ButtonColors(
                        containerColor = amparoButtonColor,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text("Híbrido")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = mapType)
            ) {
                Marker(
                    state = userCoordinatesMarker,
                    title = "Sua localização Atual",
                    snippet = "Marcador na sua localização Atual"
                )
                markers.forEach { marker ->
                    Marker(
                        state = MarkerState(position = marker.coordinates),
                        title = marker.name,
                        snippet = marker.description
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AmparoScreenPreview() {
    AmparoScreen()
}