package com.table.tatu.amparo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.services.AmparoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState<out T>{
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}


class HomeScreenViewModel(
    private val amparoService: AmparoService
): ViewModel() {
    private val _postsState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val postsState: StateFlow<UiState<List<Post>>> = _postsState

    init {
        fetchPosts()
    }

    private fun fetchPosts(){
        viewModelScope.launch {
            try {
                _postsState.value = UiState.Loading
                amparoService.getAllPosts().collect { posts ->
                    _postsState.value = UiState.Success(posts)
                }
            } catch (e: Exception){
                _postsState.value = UiState.Error("Erro ao carregar posts: ${e.message}")
            }
        }
    }
}