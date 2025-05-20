package app.example.details

sealed class DetailsEvent {
    data class LoadMovieDetails(val movieId: Int) : DetailsEvent()
    data class LoadMovieReviews(val movieId: Int) : DetailsEvent()
    data class ToggleFavorite(val movieId: Int) : DetailsEvent()
}