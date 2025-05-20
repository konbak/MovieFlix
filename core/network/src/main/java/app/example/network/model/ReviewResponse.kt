package app.example.network.model

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
)
