package app.example.home

sealed class UiEvent {
    data class FavoriteStatusMessage(val added: Boolean, val movieTitle: String) : UiEvent()
}