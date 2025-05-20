package app.example.data.mapper

import app.example.domain.model.SimilarMovieDomain
import app.example.network.model.SimilarMovie

class SimilarMoviesMapper {

    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun mapToDomain(movie: SimilarMovie): SimilarMovieDomain {
        return SimilarMovieDomain(
            id = movie.id,
            posterPath = movie.posterPath?.let { IMAGE_BASE_URL + it } ?: ""
        )
    }

    fun mapToDomainList(movies: List<SimilarMovie>): List<SimilarMovieDomain> {
        return movies
            .take(6)
            .map { mapToDomain(it) }
    }
}