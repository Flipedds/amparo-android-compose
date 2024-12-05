package com.table.tatu.amparo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.ui.states.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostScreenViewModel(
    private val amparoService: AmparoService
) : ViewModel() {
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    private val _postsState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val postsState: StateFlow<UiState<List<Post>>> = _postsState

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _postsState.value = UiState.Loading
                val allPosts: List<Post> = amparoService.getAllPosts()
                _postsState.value = UiState.Success(allPosts)
            } catch (e: Exception) {
                _postsState.value = UiState.Error("Erro ao carregar posts: ${e.message}")
            }
        }
    }
    fun refreshPosts() {
        val tempState = _postsState.value
        viewModelScope.launch {
            try {
                _postsState.value = UiState.Loading
                val allPosts: List<Post> = amparoService.getAllPosts()
                _postsState.value = UiState.Success(allPosts)
            } catch (e: Exception) {
                _toastEvent.emit("Erro ao recarregar posts: ${e.message}")
                _postsState.value = tempState
            }
        }
    }
}