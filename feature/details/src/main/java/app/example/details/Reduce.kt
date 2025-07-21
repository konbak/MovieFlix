package app.example.details

fun reduce(state: DetailsUiState, result: DetailsResult): DetailsUiState {
    return when (result) {
        is DetailsResult.Loading -> state.copy(isLoading = true, error = null)

        is DetailsResult.MovieLoaded -> state.copy(
            isLoading = false,
            movie = result.movie,
            isFavorite = result.isFavorite,
            error = null
        )

        is DetailsResult.MovieLoadError -> state.copy(isLoading = false, error = result.message)

        is DetailsResult.ReviewsLoaded -> state.copy(reviews = result.reviews, reviewsError = null)
        is DetailsResult.ReviewsLoadError -> state.copy(reviewsError = result.message)

        is DetailsResult.SimilarMoviesLoaded -> state.copy(similarMovies = result.movies, similarMoviesError = null)
        is DetailsResult.SimilarMoviesLoadError -> state.copy(similarMoviesError = result.message)

        is DetailsResult.FavoriteToggled -> state.copy(isFavorite = result.isFavorite)
    }
}