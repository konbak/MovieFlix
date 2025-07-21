package app.example.details

import app.example.domain.model.MovieDetailsDomain
import app.example.domain.model.ReviewDomain
import app.example.domain.model.SimilarMovieDomain

sealed class DetailsResult {
    object Loading : DetailsResult()

    data class MovieLoaded(val movie: MovieDetailsDomain, val isFavorite: Boolean) : DetailsResult()
    data class MovieLoadError(val message: String) : DetailsResult()

    data class ReviewsLoaded(val reviews: List<ReviewDomain>) : DetailsResult()
    data class ReviewsLoadError(val message: String) : DetailsResult()

    data class SimilarMoviesLoaded(val movies: List<SimilarMovieDomain>) : DetailsResult()
    data class SimilarMoviesLoadError(val message: String) : DetailsResult()

    data class FavoriteToggled(val isFavorite: Boolean) : DetailsResult()
}