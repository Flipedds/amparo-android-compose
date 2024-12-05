package com.table.tatu.amparo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.table.tatu.amparo.extensions.isCPFValid
import com.table.tatu.amparo.extensions.isDifferent
import com.table.tatu.amparo.extensions.isEmailValid
import com.table.tatu.amparo.extensions.isEqualsAndNotBlankAndValid
import com.table.tatu.amparo.extensions.isPassValid
import com.table.tatu.amparo.ui.states.CadastroFormState
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import io.github.skeptick.inputmask.compose.phone.PhoneInputMaskVisualTransformation

@Composable
fun CadastroForm(
    onNavigateToLogin: () -> Unit,
    onCreateNewUser: (CadastroFormState) -> Unit
) {
    var formState by remember { mutableStateOf(CadastroFormState()) }
    var isFormFilled by remember { mutableStateOf(false) }
    var isPassAndConfirmEquals by remember { mutableStateOf(false) }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    var confirmPasswordVisibility: Boolean by remember { mutableStateOf(false) }
    var isPasswordValid: Boolean by remember { mutableStateOf(false) }
    var isEmailValid: Boolean by remember { mutableStateOf(false) }
    var isCpfValid: Boolean by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    LaunchedEffect(formState) {
        isFormFilled = formState.run {
                    nome.isNotBlank()
                    && cpf.isNotBlank()
                    && cpf.isCPFValid()
                    && email.isNotBlank()
                    && email.isEmailValid()
                    && phoneNumber.isNotBlank()
                    && age.isNotBlank()
                    && senha.isEqualsAndNotBlankAndValid(confirmarSenha)
        }
        isPassAndConfirmEquals = formState.run {
            senha.isDifferent(confirmarSenha)
        }

        isPasswordValid = formState.run {
            senha.isPassValid()
        }

        isEmailValid = formState.run {
            email.isEmailValid()
        }

        isCpfValid = formState.run {
            cpf.isCPFValid()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
            .padding(horizontal = 40.dp)
    ) {
        if (scrollState.value == scrollState.maxValue) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = "Arrow Up",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp)
            )
        }

        if (scrollState.value < scrollState.maxValue) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Arrow Down",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }

        Column(
            Modifier
                .height(310.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Nome Completo",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
                    .offset(x = 20.dp, y = 0.dp)
            )

            TextField(
                value = formState.nome,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15),
                onValueChange = {
                    formState = formState.copy(nome = it)
                }, modifier = Modifier
                    .testTag("nome field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Idade",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
                    .offset(x = 20.dp, y = 0.dp)
            )

            val ageMask = "[000]"
            val ageVisualTransformation = remember(ageMask) {
                PhoneInputMaskVisualTransformation(ageMask)
            }

            TextField(
                value = formState.age,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15),
                visualTransformation = ageVisualTransformation,
                onValueChange = {
                    formState = formState.copy(age = ageVisualTransformation.sanitize(it))
                }, modifier = Modifier
                    .testTag("idade field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Cpf",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
                    .offset(x = 20.dp, y = 0.dp)
            )

            val mask = "[000].[000].[000]-[00]"
            val visualTransformation = remember(mask) {
                PhoneInputMaskVisualTransformation(mask)
            }

            TextField(
                value = formState.cpf,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15),
                visualTransformation = visualTransformation,
                onValueChange = {
                    formState = formState.copy(cpf = visualTransformation.sanitize(it))
                }, modifier = Modifier
                    .testTag("cpf field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "E-mail",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
                    .offset(x = 20.dp, y = 0.dp)
            )

            TextField(
                value = formState.email,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15),
                onValueChange = {
                    formState = formState.copy(email = it)
                }, modifier = Modifier
                    .testTag("email field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Telefone",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.Start)
                    .offset(x = 20.dp, y = 0.dp)
            )

            val phoneMask = "([00]) [0][0000]-[0000]"
            val visualTransformationPhone = remember(phoneMask) {
                PhoneInputMaskVisualTransformation(phoneMask)
            }

            TextField(
                value = formState.phoneNumber,
                visualTransformation = visualTransformationPhone,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15),
                onValueChange = {
                    formState = formState.copy(phoneNumber = visualTransformationPhone.sanitize(it))
                }, modifier = Modifier
                    .testTag("phone field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Senha",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.Start)
                    .offset(x = 20.dp, y = 0.dp)
            )

            TextField(
                value = formState.senha,
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

                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(painter = painterResource(id = image), description)
                    }
                },
                shape = RoundedCornerShape(15),
                onValueChange = {
                    formState = formState.copy(senha = it)
                }, modifier = Modifier
                    .testTag("senha field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Confirmar Senha",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.Start)
                    .offset(x = 20.dp, y = 0.dp)
            )

            TextField(
                value = formState.confirmarSenha,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (confirmPasswordVisibility)
                        R.drawable.ic_visible
                    else R.drawable.ic_visible_off

                    val description =
                        if (confirmPasswordVisibility) "Hide password" else "Show password"

                    IconButton(onClick = {
                        confirmPasswordVisibility = !confirmPasswordVisibility
                    }) {
                        Icon(painter = painterResource(id = image), description)
                    }
                },
                shape = RoundedCornerShape(15),
                onValueChange = {
                    formState = formState.copy(confirmarSenha = it)
                }, modifier = Modifier
                    .testTag("confirmar senha field")
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            )
        }

        AnimatedVisibility(visible = isPassAndConfirmEquals) {
            Text(
                text = "Senhas devem ser iguais",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(x = 0.dp, y = 0.dp)
            )
        }

        AnimatedVisibility(visible = !isPasswordValid) {
            Text(
                text = "Senha inválida",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(x = 0.dp, y = 0.dp)
            )
        }

        AnimatedVisibility(visible = !isEmailValid) {
            Text(
                text = "E-mail inválido",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(x = 0.dp, y = 0.dp)
            )
        }

        AnimatedVisibility(visible = !isCpfValid) {
            Text(
                text = "CPF inválido",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(x = 0.dp, y = 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                onCreateNewUser(formState)
            },
            modifier = Modifier
                .testTag("enviar button")
                .align(Alignment.CenterHorizontally)
                .size(width = 170.dp, height = 50.dp),
            enabled = isFormFilled,
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
    CadastroForm(onNavigateToLogin = {}, onCreateNewUser = { _: CadastroFormState -> })
}