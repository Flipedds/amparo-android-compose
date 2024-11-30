package com.table.tatu.amparo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.enums.Relation
import com.table.tatu.amparo.models.SupportNetwork
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.services.UserPreferences
import com.table.tatu.amparo.ui.states.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AjudaScreenViewModel(
    private val amparoService: AmparoService,
    private val userPreferences: UserPreferences,
) : ViewModel() {
    private val _supportNetworkState =
        MutableStateFlow<UiState<MutableList<SupportNetwork>>>(UiState.Loading)
    val supportNetworkState: StateFlow<UiState<MutableList<SupportNetwork>>> = _supportNetworkState

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    init {
        fetchSupportNetwork()
    }

    private fun fetchSupportNetwork() {
        viewModelScope.launch {
            _supportNetworkState.value = UiState.Loading
            try {
                val userSupportNetwork: MutableList<SupportNetwork> =
                    amparoService.getUserSupportNetwork("Bearer ${userPreferences.jwtToken.first()}")
                _supportNetworkState.value = UiState.Success(userSupportNetwork)
            } catch (e: Exception) {
                _toastEvent.emit("Erro ao carregar rede: ${e.message}")
                _supportNetworkState.value = UiState.Default
            }
        }
    }

    fun createNewSupport(name: String, phone: String, relation: Relation, onClearForm: () -> Unit) {
        viewModelScope.launch {
            val tempState = _supportNetworkState.value

            _supportNetworkState.value = UiState.Loading
            try {
                val userSupportNetwork: MutableList<SupportNetwork> =
                    amparoService.getUserSupportNetwork("Bearer ${userPreferences.jwtToken.first()}")

                userSupportNetwork.add(SupportNetwork(name, phone, relation))

                val updatedUserSupportNetwork = amparoService.updateUserSupportNetwork(
                    "Bearer ${userPreferences.jwtToken.first()}",
                    userSupportNetwork
                )

                onClearForm()
                _supportNetworkState.value = UiState.Success(updatedUserSupportNetwork)

            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> _toastEvent.emit("Requisição inválida! Verifique os dados e tente novamente.")
                    401 -> _toastEvent.emit("Usuária não autenticada, refaça o login, por favor. ")
                    403 -> _toastEvent.emit("Acesso negado!")
                    500 -> _toastEvent.emit("Erro no servidor. Tente novamente mais tarde.")
                    else -> _toastEvent.emit("Erro inesperado: ${e.message()}")
                }
            } catch (e: IOException) {
                _toastEvent.emit("Falha na conexão com o servidor. Verifique sua internet e tente novamente.")
                _supportNetworkState.value = tempState
            } catch (e: Exception) {
                _toastEvent.emit("Ocorreu um erro inesperado: ${e.localizedMessage ?: "Tente novamente mais tarde."}")
                _supportNetworkState.value = tempState
            }
        }
    }
}