package org.example.project.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val name: String,
    val summary: String? = null,
    @SerialName("cover")
    val coverUrl: String? = null,
    @SerialName("first_release_date")
    val releaseDate: Long? = null,
    val rating: Double? = null,
    @SerialName("rating_count")
    val ratingCount: Int? = null,
    val platforms: List<Platform>? = null,
    val genres: List<Genre>? = null,
    val screenshots: List<Screenshot>? = null
)

@Serializable
data class Platform(
    val id: Int,
    val name: String
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)

@Serializable
data class Screenshot(
    val id: Int,
    val url: String
)

@Serializable
data class GameResponse(
    val results: List<Game>
)