package com.table.tatu.amparo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.ui.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostScreenViewModel(
    private val amparoService: AmparoService
) : ViewModel() {
    private val _postsState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val postsState: StateFlow<UiState<List<Post>>> = _postsState

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _postsState.value = UiState.Loading
                amparoService.getAllPosts().collect { posts ->
                    _postsState.value = UiState.Success(posts)
                }
            } catch (e: Exception) {
                _postsState.value = UiState.Error("Erro ao carregar posts: ${e.message}")
            }
        }
    }
}