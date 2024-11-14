package com.table.tatu.amparo.components

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoDefaultColor

@Composable
fun CadastroForm(
    onNavigateToLogin: () -> Unit,
    location: Location?
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
            .padding(horizontal = 40.dp)) {
        Text(
            text = "Telefone ou E-mail",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 5.dp)
                .offset(x = 20.dp, y = 0.dp))

        var email by remember {
            mutableStateOf("")
        }

        TextField(
            value = email,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15),
            onValueChange = {
                email = it
            }, modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth())

        Text(
            text = "Senha",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .align(Alignment.Start)
                .offset(x = 20.dp, y = 0.dp))

        var senha by remember {
            mutableStateOf("")
        }

        TextField(
            value = senha,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15),
            onValueChange = {
                senha = it
            }, modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth())

        Text(
            text = "Localização",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .align(Alignment.Start)
                .offset(x = 20.dp, y = 0.dp))

        var localizacao by remember {
            mutableStateOf("")
        }

        localizacao = "${location?.latitude} ${location?.longitude}"

        TextField(
            value = localizacao,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15),
            onValueChange = {
                localizacao = it
            }, modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth())

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = { },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 170.dp, height = 50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = amparoButtonColor,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            )
        ) {
            Text(text = "Enviar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Já tenho uma conta",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally)
                .offset(x = 60.dp, y = 0.dp)
                .clickable { onNavigateToLogin() })
    }
}

@Preview
@Composable
private fun CadastroFormPreview() {
    CadastroForm(onNavigateToLogin = {}, location = null)
}