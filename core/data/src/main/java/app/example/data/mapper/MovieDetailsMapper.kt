package app.example.data.mapper

import app.example.domain.model.MovieDetailsDomain
import app.example.network.model.MovieDetails

class MovieDetailsMapper(
    private val dateFormatter: DateFormatter
) {
    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun mapToDomain(movie: MovieDetails): MovieDetailsDomain {
        return MovieDetailsDomain(
            title = movie.title,
            voteAverage = movie.voteAverage,
            posterUrl = movie.posterPath?.let { IMAGE_BASE_URL + it } ?: "",
            homepage = movie.homepage,
            overview = movie.overview,
            genres = movie.genres.map { it.name },
            releaseDate = dateFormatter.formatDate(movie.releaseDate),
            runtime = formatRuntime(movie.runtime),
        )
    }

    private fun formatRuntime(runtimeInMinutes: Int): String {
        val hours = runtimeInMinutes / 60
        val minutes = runtimeInMinutes % 60
        return "${hours}h ${minutes}m"
    }
}