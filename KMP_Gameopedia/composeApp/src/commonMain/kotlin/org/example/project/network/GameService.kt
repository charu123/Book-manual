package org.example.project.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import org.example.project.data.Game
import org.example.project.data.Genre
import org.example.project.data.Platform

class GameService(private val httpClient: HttpClient) {
    
    suspend fun searchGames(query: String): List<Game> {
        return try {
            // For demo purposes, we'll use mock data instead of a real API
            // This prevents actual HTTP timeout issues
            delay(1000) // Simulate network delay
            getMockGames().filter { 
                it.name.contains(query, ignoreCase = true) 
            }
        } catch (e: Exception) {
            println("Error searching games: ${e.message}")
            // Return fallback data in case of error
            getMockGames().take(3)
        }
    }
    
    suspend fun getPopularGames(): List<Game> {
        return try {
            // For demo purposes, we'll use mock data
            delay(800) // Simulate network delay
            getMockGames().sortedByDescending { it.rating }
        } catch (e: Exception) {
            println("Error fetching popular games: ${e.message}")
            getMockGames()
        }
    }
    
    suspend fun getGameDetails(gameId: Int): Game? {
        return try {
            delay(500) // Simulate network delay
            getMockGames().find { it.id == gameId }
        } catch (e: Exception) {
            println("Error fetching game details: ${e.message}")
            null
        }
    }
    
    private fun getMockGames(): List<Game> {
        return listOf(
            Game(
                id = 1,
                name = "The Witcher 3: Wild Hunt",
                summary = "The Witcher 3: Wild Hunt is a story-driven, next-generation open world role-playing game set in a visually stunning fantasy universe full of meaningful choices and impactful consequences.",
                coverUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1wyy.jpg",
                releaseDate = 1431648000L, // May 2015
                rating = 93.5,
                ratingCount = 2847,
                platforms = listOf(
                    Platform(6, "PC"),
                    Platform(48, "PlayStation 4"),
                    Platform(49, "Xbox One"),
                    Platform(130, "Nintendo Switch")
                ),
                genres = listOf(
                    Genre(12, "Role-playing (RPG)"),
                    Genre(31, "Adventure")
                )
            ),
            Game(
                id = 2,
                name = "Cyberpunk 2077",
                summary = "Cyberpunk 2077 is an open-world, action-adventure story set in Night City, a megalopolis obsessed with power, glamour and body modification.",
                coverUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1wyy.jpg",
                releaseDate = 1607558400L, // December 2020
                rating = 71.2,
                ratingCount = 1523,
                platforms = listOf(
                    Platform(6, "PC"),
                    Platform(48, "PlayStation 4"),
                    Platform(49, "Xbox One"),
                    Platform(167, "PlayStation 5"),
                    Platform(169, "Xbox Series X|S")
                ),
                genres = listOf(
                    Genre(12, "Role-playing (RPG)"),
                    Genre(5, "Shooter")
                )
            ),
            Game(
                id = 3,
                name = "Elden Ring",
                summary = "Elden Ring is a fantasy action-RPG adventure set within a world created by Hidetaka Miyazaki and George R.R. Martin.",
                coverUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co4jni.jpg",
                releaseDate = 1645833600L, // February 2022
                rating = 96.3,
                ratingCount = 3421,
                platforms = listOf(
                    Platform(6, "PC"),
                    Platform(48, "PlayStation 4"),
                    Platform(49, "Xbox One"),
                    Platform(167, "PlayStation 5"),
                    Platform(169, "Xbox Series X|S")
                ),
                genres = listOf(
                    Genre(12, "Role-playing (RPG)"),
                    Genre(31, "Adventure")
                )
            ),
            Game(
                id = 4,
                name = "Red Dead Redemption 2",
                summary = "Red Dead Redemption 2 is a 2018 action-adventure game developed and published by Rockstar Games.",
                coverUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q1f.jpg",
                releaseDate = 1540425600L, // October 2018
                rating = 95.1,
                ratingCount = 2156,
                platforms = listOf(
                    Platform(6, "PC"),
                    Platform(48, "PlayStation 4"),
                    Platform(49, "Xbox One")
                ),
                genres = listOf(
                    Genre(31, "Adventure"),
                    Genre(5, "Shooter")
                )
            ),
            Game(
                id = 5,
                name = "God of War",
                summary = "His vengeance against the Gods of Olympus years behind him, Kratos now lives as a man in the realm of Norse Gods and monsters.",
                coverUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1tmu.jpg",
                releaseDate = 1524182400L, // April 2018
                rating = 94.2,
                ratingCount = 1987,
                platforms = listOf(
                    Platform(6, "PC"),
                    Platform(48, "PlayStation 4"),
                    Platform(167, "PlayStation 5")
                ),
                genres = listOf(
                    Genre(25, "Hack and slash/Beat 'em up"),
                    Genre(31, "Adventure")
                )
            ),
            Game(
                id = 6,
                name = "Horizon Zero Dawn",
                summary = "Horizon Zero Dawn is an action role-playing game developed by Guerrilla Games and published by Sony Interactive Entertainment.",
                coverUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1u4g.jpg",
                releaseDate = 1488326400L, // February 2017
                rating = 89.7,
                ratingCount = 1654,
                platforms = listOf(
                    Platform(6, "PC"),
                    Platform(48, "PlayStation 4"),
                    Platform(167, "PlayStation 5")
                ),
                genres = listOf(
                    Genre(12, "Role-playing (RPG)"),
                    Genre(31, "Adventure")
                )
            )
        )
    }
}