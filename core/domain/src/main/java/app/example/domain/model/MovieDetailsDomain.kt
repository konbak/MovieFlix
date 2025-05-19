package app.example.domain.model

data class MovieDetailsDomain(
    val title: String,
    val voteAverage: Double,
    val posterUrl: String,
    val homepage: String?,
    val overview: String,
    val genres: List<String>,
    val releaseDate: String,
    val runtime: String,
)
