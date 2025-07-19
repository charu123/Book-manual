package org.example.project.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.project.data.Game
import org.example.project.network.GameService

class GameRepository(private val gameService: GameService) {
    
    private var cachedPopularGames: List<Game>? = null
    private var lastFetchTime: Long = 0
    private val cacheValidityDuration = 5 * 60 * 1000L // 5 minutes
    
    suspend fun getPopularGames(): Flow<Result<List<Game>>> = flow {
        emit(Result.Loading())
        
        try {
            // Check if we have valid cached data
            val currentTime = System.currentTimeMillis()
            if (cachedPopularGames != null && 
                (currentTime - lastFetchTime) < cacheValidityDuration) {
                emit(Result.Success(cachedPopularGames!!))
                return@flow
            }
            
            // Fetch fresh data
            val games = gameService.getPopularGames()
            cachedPopularGames = games
            lastFetchTime = currentTime
            emit(Result.Success(games))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
        }
    }
    
    suspend fun searchGames(query: String): Flow<Result<List<Game>>> = flow {
        emit(Result.Loading())
        
        try {
            if (query.isBlank()) {
                emit(Result.Success(emptyList()))
                return@flow
            }
            
            val games = gameService.searchGames(query)
            emit(Result.Success(games))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Search failed"))
        }
    }
    
    suspend fun getGameDetails(gameId: Int): Flow<Result<Game?>> = flow {
        emit(Result.Loading())
        
        try {
            val game = gameService.getGameDetails(gameId)
            emit(Result.Success(game))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to load game details"))
        }
    }
    
    sealed class Result<out T> {
        class Loading<T> : Result<T>()
        data class Success<T>(val data: T) : Result<T>()
        data class Error<T>(val message: String) : Result<T>()
    }
}