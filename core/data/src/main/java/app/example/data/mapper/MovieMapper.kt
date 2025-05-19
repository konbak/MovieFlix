package app.example.data.mapper

import app.example.domain.model.MovieDomain
import app.example.network.model.Movie

class MovieMapper(
    private val dateFormatter: DateFormatter
) {
    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun mapToDomain(movie: Movie): MovieDomain {
        return MovieDomain(
            id = movie.id,
            title = movie.title,
            voteAverage = movie.voteAverage,
            posterPath = movie.posterPath?.let { IMAGE_BASE_URL + it } ?: "",
            releaseDate = dateFormatter.formatDate(movie.releaseDate)
        )
    }
}