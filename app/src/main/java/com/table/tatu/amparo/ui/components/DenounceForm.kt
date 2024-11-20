package com.table.tatu.amparo.ui.components

import android.location.Location
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenounceForm(
    onCreateDenounce: (String, String, () -> Unit) -> Unit,
    location: Location?
) {
    Column {
        Text(
            text = "Tipo de violência",
            fontFamily = grandstanderFontFamily,
            fontSize = 15.sp,
            color = amparoHomeTextColor,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 5.dp)
        )

        var selectedItem by remember { mutableStateOf("") }

        var expanded by remember { mutableStateOf(false) }

        val options = listOf(
            "Violência Física",
            "Violência Psicológica",
            "Violência Sexual",
            "Violência Patrimonial"
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = { },
                readOnly = true,
                shape = RoundedCornerShape(15),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, fontFamily = grandstanderFontFamily) },
                        onClick = {
                            selectedItem = option
                            expanded = false
                        }
                    )
                }
            }
        }

        var needDetails by remember { mutableStateOf(true) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Column {
                Text(
                    "Se você se sentir confortável, ",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 15.sp
                )
                Text(
                    "pode compartilhar mais detalhes",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 15.sp
                )
                Text(
                    "sobre o que aconteceu?",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Checkbox(
                modifier = Modifier,
                checked = needDetails,
                onCheckedChange = { needDetails = it }
            )
        }

        var detalhes by remember {
            mutableStateOf("")
        }

        AnimatedVisibility(visible = needDetails) {
            TextField(
                value = detalhes,
                minLines = 5,
                maxLines = 15,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    detalhes = it
                }, modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )
        }

        var localizacao by remember {
            mutableStateOf("")
        }

        localizacao = "${location?.latitude} ${location?.longitude}"

        location?.let {
            Text(
                text = "Sua Localização",
                fontFamily = grandstanderFontFamily,
                fontSize = 15.sp,
                color = amparoHomeTextColor,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp, top = 10.dp)
            )

            val userCoordinates = LatLng(location.latitude, location.longitude)
            val userCoordinatesMarker = rememberMarkerState(position = userCoordinates)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(userCoordinates, 17f)
            }
            val mapType by remember {
                mutableStateOf(MapType.HYBRID)
            }

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 20.dp),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = mapType)
            ) {
                Marker(
                    state = userCoordinatesMarker,
                    title = "Sua localização Atual",
                    snippet = "Marcador na sua localização Atual"
                )
            }
        }

        var isFormFilled by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = selectedItem, key2 = detalhes, key3 = localizacao) {
            isFormFilled =
                if (selectedItem.isNotBlank() && needDetails && detalhes.isNotBlank() && localizacao.isNotBlank()) {
                    true
                } else if (selectedItem.isNotBlank() && !needDetails && localizacao.isNotBlank()) {
                    true
                } else {
                    false
                }
        }

        Button(
            onClick = {
                onCreateDenounce(selectedItem, detalhes) {
                    selectedItem = ""
                    detalhes = ""
                }
            },
            enabled = isFormFilled,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 300.dp, height = 50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = amparoButtonColor,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            )
        ) {
            Text(
                text = "Enviar Denúncia",
                fontSize = 20.sp,
                fontFamily = grandstanderFontFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DenounceFormPreview() {
    DenounceForm(
        onCreateDenounce = { _: String, _: String, _: () -> Unit -> },
        location = Location("-8.049733954705594, -34.88226562591665")
    )
}
