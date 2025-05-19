package app.example.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("poster_path")
    val posterPath: String?,

    val homepage: String?,

    val overview: String,

    val genres: List<Genre>,

    @SerializedName("release_date")
    val releaseDate: String,

    val runtime: Int,
)
