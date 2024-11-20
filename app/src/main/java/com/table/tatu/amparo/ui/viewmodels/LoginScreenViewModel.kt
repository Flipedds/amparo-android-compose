package com.table.tatu.amparo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.services.UserPreferences
import com.table.tatu.amparo.ui.states.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginScreenViewModel(
    private val userPreferences: UserPreferences,
    private val amparoService: AmparoService
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    private val _uiState = MutableStateFlow<UiState<Nothing>>(UiState.Default)
    val uiState: StateFlow<UiState<Nothing>> = _uiState

    init {
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { isLoggedIn ->
                _isLoggedIn.value = isLoggedIn
            }
        }
    }

    fun authenticateUser(login: String, senha: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val authenticateUser = amparoService.authenticateUser(login, senha)
                userPreferences.setJwtToken(authenticateUser.accessToken)
                setLoggedIn(true)
                onSuccess()
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> _toastEvent.emit("Requisição inválida! Verifique os dados e tente novamente.")
                    401 -> _toastEvent.emit("Login ou senha incorretos!")
                    403 -> _toastEvent.emit("Acesso negado!")
                    500 -> _toastEvent.emit("Erro no servidor. Tente novamente mais tarde.")
                    else -> _toastEvent.emit("Erro inesperado: ${e.message()}")
                }
            } catch (e: IOException) {
                _toastEvent.emit("Falha na conexão com o servidor. Verifique sua internet e tente novamente.")
            } catch (e: Exception) {
                _toastEvent.emit("Ocorreu um erro inesperado: ${e.localizedMessage ?: "Tente novamente mais tarde."}")
            } finally {
                _uiState.value = UiState.Default
            }
        }
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        viewModelScope.launch {
            userPreferences.setLoggedIn(isLoggedIn)
        }
    }
}