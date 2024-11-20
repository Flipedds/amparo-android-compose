package com.table.tatu.amparo.ui.viewmodels

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.services.LocationService
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

class DenounceScreenViewModel(
    private val locationService: LocationService,
    private val amparoService: AmparoService,
    private val userPreferences: UserPreferences
) : ViewModel() {
    private val _permissionGranted = MutableStateFlow(false)
    val permissionGranted: StateFlow<Boolean> = _permissionGranted

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    private val _uiState = MutableStateFlow<UiState<Nothing>>(UiState.Default)
    val uiState: StateFlow<UiState<Nothing>> = _uiState

    fun createNewDenounce(name: String, description: String, onClearForm: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                Log.i("DEBUG", " ${userPreferences.jwtToken.first()}")
                amparoService.newDenounce("Bearer ${userPreferences.jwtToken.first()}", name, description)
                _toastEvent.emit("Denúncia enviada com sucesso !!")
                onClearForm()
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
            } catch (e: Exception) {
                _toastEvent.emit("Ocorreu um erro inesperado: ${e.localizedMessage ?: "Tente novamente mais tarde."}")
            } finally {
                _uiState.value = UiState.Default
            }
        }
    }

    fun checkPermission() {
        _permissionGranted.value = locationService.hasLocationPermission()
    }

    fun fetchLocation() {
        if (_permissionGranted.value) {
            locationService.getLastLocation { _location.value = it }
        }
    }
}