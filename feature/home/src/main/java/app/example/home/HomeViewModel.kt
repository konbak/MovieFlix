package app.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.example.domain.model.MovieDomain
import app.example.domain.shared.FavoritesManager
import app.example.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val favoritesManager: FavoritesManager,
    private val snackbarManager: SnackbarManager
) : ViewModel() {

    val moviesFlow: Flow<PagingData<MovieDomain>> = getPopularMoviesUseCase()
        .cachedIn(viewModelScope)

    val favoriteIds = favoritesManager.favoriteIds

    val snackbarMessages = snackbarManager.events

    fun toggleFavorite(movie: MovieDomain) {
        favoritesManager.toggleFavorite(movie.id)

        viewModelScope.launch {
            val isFavorite = favoritesManager.isFavorite(movie.id)
            snackbarManager.emit(
                UiEvent.FavoriteStatusMessage(
                    added = isFavorite,
                    movieTitle = movie.title
                )
            )
        }
    }
}
