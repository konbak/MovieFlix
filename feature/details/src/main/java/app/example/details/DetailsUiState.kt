package app.example.details

import app.example.domain.model.MovieDetailsDomain
import app.example.domain.model.ReviewDomain
import app.example.domain.model.SimilarMovieDomain
import app.example.domain.shared.DomainError

data class DetailsUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetailsDomain? = null,
    val reviews: List<ReviewDomain> = emptyList(),
    val similarMovies: List<SimilarMovieDomain> = emptyList(),
    val error: DomainError? = null,
    val reviewsError: DomainError? = null,
    val similarMoviesError: DomainError? = null,
    val isFavorite: Boolean = false
)
