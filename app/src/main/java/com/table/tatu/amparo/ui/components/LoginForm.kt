package com.table.tatu.amparo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoDefaultColor

@Composable
fun LoginForm(
    onNavigateToHome: (String, String) -> Unit,
    onNavigateToCadastro: () -> Unit
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
                .testTag("email field")
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

        var passwordVisibility: Boolean by remember { mutableStateOf(false) }

        TextField(
            value = senha,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisibility)
                    R.drawable.ic_visible
                else R.drawable.ic_visible_off

                val description = if (passwordVisibility) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisibility = !passwordVisibility}){
                    Icon(painter = painterResource(id = image), description)
                }
            },
            shape = RoundedCornerShape(15),
            onValueChange = {
                senha = it
            }, modifier = Modifier
                .testTag("senha field")
                .padding(bottom = 20.dp)
                .fillMaxWidth())

        Spacer(modifier = Modifier.height(50.dp))

        var isFormFilled by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = email, key2 = senha) {
            isFormFilled = email.isNotBlank() && senha.isNotBlank()
        }

        Button(
            enabled = isFormFilled,
            onClick = { onNavigateToHome(email, senha)},
            modifier = Modifier
                .testTag("entrar button")
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
            Text(text = "Entrar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = { onNavigateToCadastro()},
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
            Text(text = "Cadastrar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
private fun LoginFormPreview() {
    LoginForm(onNavigateToHome = { _: String, _: String -> }, onNavigateToCadastro = {})
}