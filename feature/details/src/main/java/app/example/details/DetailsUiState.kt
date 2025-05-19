package app.example.details

import app.example.domain.model.MovieDetailsDomain

data class DetailsUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetailsDomain? = null,
    val error: String? = null,
    val isFavorite: Boolean = false
)
