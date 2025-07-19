package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.example.project.data.Game
import org.example.project.repository.GameRepository

class GameViewModel(private val repository: GameRepository) : ViewModel() {
    
    var uiState by mutableStateOf(GameUiState())
        private set
    
    private var searchJob: Job? = null
    
    init {
        loadPopularGames()
    }
    
    fun loadPopularGames() {
        viewModelScope.launch {
            repository.getPopularGames().collectLatest { result ->
                when (result) {
                    is GameRepository.Result.Loading -> {
                        uiState = uiState.copy(isLoading = true, errorMessage = null)
                    }
                    is GameRepository.Result.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            games = result.data,
                            errorMessage = null
                        )
                    }
                    is GameRepository.Result.Error -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun updateSearchQuery(query: String) {
        uiState = uiState.copy(searchQuery = query)
        if (query.isBlank()) {
            loadPopularGames()
        }
    }
    
    fun searchGames() {
        val query = uiState.searchQuery.trim()
        if (query.isBlank()) {
            loadPopularGames()
            return
        }
        
        // Cancel previous search job
        searchJob?.cancel()
        
        searchJob = viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            
            try {
                delay(300) // Debounce
                repository.searchGames(query).collectLatest { result ->
                    when (result) {
                        is GameRepository.Result.Loading -> {
                            uiState = uiState.copy(isLoading = true, errorMessage = null)
                        }
                        is GameRepository.Result.Success -> {
                            uiState = uiState.copy(
                                isLoading = false,
                                games = result.data,
                                errorMessage = null
                            )
                        }
                        is GameRepository.Result.Error -> {
                            uiState = uiState.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Search failed"
                )
            }
        }
    }
}

data class GameUiState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null
)