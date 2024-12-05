package com.table.tatu.amparo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.enums.Relation
import com.table.tatu.amparo.extensions.brName
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily
import io.github.skeptick.inputmask.compose.phone.PhoneInputMaskVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportNetworkForm(
    onNewSupport: (String, String, Relation,  () -> Unit) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
            .padding(bottom = 5.dp)) {
        Text(
            text = "Nome",
            fontFamily = grandstanderFontFamily,
            fontSize = 20.sp,
            color = amparoHomeTextColor,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 5.dp))

        var name by remember {
            mutableStateOf("")
        }

        TextField(
            value = name,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15),
            onValueChange = {
                name = it
            }, modifier = Modifier
                .testTag("name field")
                .padding(bottom = 20.dp)
                .fillMaxWidth())

        Text(
            text = "Telefone",
            fontFamily = grandstanderFontFamily,
            fontSize = 20.sp,
            color = amparoHomeTextColor,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 5.dp))

        var phone by remember {
            mutableStateOf("")
        }

        val mask = "([00]) [0][0000]-[0000]"
        val visualTransformation = remember(mask) {
            PhoneInputMaskVisualTransformation(mask)
        }

        TextField(
            value = phone,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15),
            onValueChange = {
                phone = visualTransformation.sanitize(it)
            }, modifier = Modifier
                .testTag("phone field")
                .padding(bottom = 20.dp)
                .fillMaxWidth())

        Text(
            text = "Relação",
            fontFamily = grandstanderFontFamily,
            fontSize = 20.sp,
            color = amparoHomeTextColor,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 5.dp)
        )

        var selectedItem by remember { mutableStateOf(Relation.RELATIVE) }

        var expanded by remember { mutableStateOf(false) }

        val options = listOf(
            Relation.FRIEND,
            Relation.FATHER,
            Relation.MOTHER,
            Relation.NEIGHBOR,
            Relation.RELATIVE
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedItem.brName(),
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
                        text = { Text(option.brName(), fontFamily = grandstanderFontFamily) },
                        onClick = {
                            selectedItem = option
                            expanded = false
                        }
                    )
                }
            }
        }

        var isFormFilled by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = name, key2 = phone) {
            isFormFilled = name.isNotBlank() && phone.isNotBlank()
        }

        Button(
            enabled = isFormFilled,
            onClick = {
                onNewSupport(name, phone, selectedItem){
                    name = ""
                    phone = ""
                    selectedItem = Relation.RELATIVE
                }
            },
            modifier = Modifier
                .padding(top = 25.dp)
                .testTag("enviar button")
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
    }
}

@Preview
@Composable
private fun SupportNetworkFormPreview() {
    SupportNetworkForm(onNewSupport = {_: String, _: String, _: Relation, _:() -> Unit ->})
}