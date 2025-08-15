package app.example.home

sealed interface HomeEffect {
    data class NavigateToDetails(val movieId: Int) : HomeEffect
}