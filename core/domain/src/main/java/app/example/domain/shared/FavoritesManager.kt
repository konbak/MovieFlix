package app.example.domain.shared

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesManager @Inject constructor() {

    private val _favoriteIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteIds: StateFlow<Set<Int>> = _favoriteIds

    fun toggleFavorite(movieId: Int) {
        _favoriteIds.update { current ->
            if (current.contains(movieId)) current - movieId
            else current + movieId
        }
    }

    fun isFavorite(movieId: Int): Boolean {
        return _favoriteIds.value.contains(movieId)
    }
}