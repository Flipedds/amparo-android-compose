package com.table.tatu.amparo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.dtos.AuthLoginResponse
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.ui.states.CadastroFormState
import com.table.tatu.amparo.ui.states.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CadastroScreenViewModel(
    private val amparoService: AmparoService
) : ViewModel() {
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    private val _uiState = MutableStateFlow<UiState<Nothing>>(UiState.Default)
    val uiState: StateFlow<UiState<Nothing>> = _uiState

    fun createNewUser(form: CadastroFormState, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val authLoginResponse: AuthLoginResponse = amparoService.registerUser(form.email, form.senha)
                val user = amparoService.getUser("Bearer ${authLoginResponse.accessToken}")
                val userHeader = user.toUserHeader()
                val userCredential = user.toUserCredential()

                userHeader.name = form.nome
                userHeader.age = form.age
                userCredential.cpf = form.cpf
                userCredential.phoneNumber = form.phoneNumber

                amparoService.updateHeaderUser("Bearer ${authLoginResponse.accessToken}", userHeader)
                amparoService.updateCredentialUser("Bearer ${authLoginResponse.accessToken}", userCredential)

                _toastEvent.emit("Cadastro criado com sucesso.")

                onSuccess()
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> _toastEvent.emit("Requisição inválida! Verifique os dados e tente novamente.")
                    401 -> _toastEvent.emit("Autenticação não concluída.")
                    422 -> _toastEvent.emit("Usuária já existe no sistema.")
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
}