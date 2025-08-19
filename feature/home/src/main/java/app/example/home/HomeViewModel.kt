package app.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.example.domain.model.MovieDomain
import app.example.domain.shared.FavoritesManager
import app.example.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val favoritesManager: FavoritesManager,
) : ViewModel() {

    val moviesFlow: Flow<PagingData<MovieDomain>> = getPopularMoviesUseCase()
        .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(HomeState(movies = moviesFlow))
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<HomeEffect>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val effects: SharedFlow<HomeEffect> = _effects.asSharedFlow()

    init {
        viewModelScope.launch {
            favoritesManager.favoriteIds.collect { ids ->
                applyResult(HomeResult.FavoritesUpdated(ids))
            }
        }
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Refresh -> Unit
            is HomeIntent.ToggleFavorite -> handleToggleFavorite(intent.movie)
            is HomeIntent.ClickMovie -> emitEffect(HomeEffect.NavigateToDetails(intent.movieId))
        }
    }

    private fun handleToggleFavorite(movie: MovieDomain) {
        viewModelScope.launch {
            favoritesManager.toggleFavorite(movie.id)
            val isFav = favoritesManager.isFavorite(movie.id)

            emitEffect(
                HomeEffect.ShowFavoriteStatus(
                    added = isFav,
                    movieTitle = movie.title
                )
            )
        }
    }

    private fun applyResult(result: HomeResult) {
        _state.update { it.reduce(result) }
    }

    private fun emitEffect(effect: HomeEffect) {
        _effects.tryEmit(effect)
    }
}
