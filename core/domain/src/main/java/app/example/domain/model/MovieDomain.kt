package app.example.domain.model

data class MovieDomain(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String?,
    val releaseDate: String,
)
