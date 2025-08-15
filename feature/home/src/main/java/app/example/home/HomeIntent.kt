package app.example.home

import app.example.domain.model.MovieDomain

sealed interface HomeIntent {
    data object Refresh : HomeIntent
    data class ToggleFavorite(val movie: MovieDomain) : HomeIntent
    data class ClickMovie(val movieId: Int) : HomeIntent
}