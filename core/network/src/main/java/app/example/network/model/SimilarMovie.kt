package app.example.network.model

import com.google.gson.annotations.SerializedName

data class SimilarMovie(
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String?,
)
