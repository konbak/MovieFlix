package app.example.details

import app.example.domain.model.MovieDetailsDomain
import app.example.domain.model.ReviewDomain

data class DetailsUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetailsDomain? = null,
    val reviews: List<ReviewDomain> = emptyList(),
    val error: String? = null,
    val reviewsError: String? = null,
    val isFavorite: Boolean = false
)
