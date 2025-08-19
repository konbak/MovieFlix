package app.example.home

sealed interface HomeEffect {
    data class NavigateToDetails(val movieId: Int) : HomeEffect
    data class ShowFavoriteStatus(val added: Boolean, val movieTitle: String) : HomeEffect
}