package app.example.home

sealed interface HomeResult {
    data class FavoritesUpdated(val ids: Set<Int>) : HomeResult
}