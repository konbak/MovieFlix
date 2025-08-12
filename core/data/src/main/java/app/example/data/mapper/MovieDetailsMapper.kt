package app.example.data.mapper

import app.example.domain.model.MovieDetailsDomain
import app.example.network.model.MovieDetails
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor(
    private val dateFormatter: DateFormatter,
    @param:ImageBaseUrl private val imageBaseUrl: String
) {
    fun mapToDomain(movie: MovieDetails): MovieDetailsDomain {
        return MovieDetailsDomain(
            title = movie.title,
            voteAverage = movie.voteAverage,
            posterUrl = movie.posterPath?.let { imageBaseUrl + it }.orEmpty(),
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