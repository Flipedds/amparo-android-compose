package com.table.tatu.amparo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.table.tatu.amparo.enums.Relation
import com.table.tatu.amparo.models.SupportNetwork
import com.table.tatu.amparo.ui.animations.LoadingAnimation
import com.table.tatu.amparo.ui.components.SupportNetworkCard
import com.table.tatu.amparo.ui.components.SupportNetworkForm
import com.table.tatu.amparo.ui.states.UiState
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily
import com.table.tatu.amparo.ui.viewmodels.AjudaScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AjudaScreen() {
    val ajudaScreenViewModel = koinViewModel<AjudaScreenViewModel>()
    val uiState by ajudaScreenViewModel.supportNetworkState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(ajudaScreenViewModel.toastEvent) {
        ajudaScreenViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
            .padding(horizontal = 31.5.dp, vertical = 30.dp)
    ) {
        when (uiState) {
            is UiState.Loading -> LoadingAnimation(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            is UiState.Success -> {
                val supportNetwork = (uiState as UiState.Success<MutableList<SupportNetwork>>).data

                Text(
                    text = "Existem ${supportNetwork.size} pessoas em sua rede de apoio",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 25.sp,
                    color = amparoHomeTextColor,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 20.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                LazyColumn(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 31.5.dp, vertical = 5.dp)
                        .height(150.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(supportNetwork) { support ->
                        SupportNetworkCard(supportNetwork = support)
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Adicionar Pessoa a rede",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 25.sp,
                    color = amparoHomeTextColor,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 20.dp)
                )
                SupportNetworkForm(onNewSupport = { name: String, phone: String, relation: Relation, onClearForm: () -> Unit ->
                    ajudaScreenViewModel.createNewSupport(name, phone, relation, onClearForm)
                })
            }
            is UiState.Default -> {
                Text(
                    text = "Adicionar Pessoa a rede",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 25.sp,
                    color = amparoHomeTextColor,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 20.dp)
                )
                SupportNetworkForm(onNewSupport = { name: String, phone: String, relation: Relation, onClearForm: () -> Unit ->
                    ajudaScreenViewModel.createNewSupport(name, phone, relation, onClearForm)
                })
            }
            else -> TODO()
        }
    }
}

@Preview
@Composable
private fun AjudaScreenPreview() {
    AjudaScreen()
}