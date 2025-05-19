package app.example.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.domain.shared.FavoritesManager
import app.example.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val favoritesManager: FavoritesManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.LoadMovieDetails -> loadMovie(event.movieId)
            is DetailsEvent.ToggleFavorite -> toggleFavorite(event.movieId)
        }
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val movie = getMovieDetailsUseCase(movieId)
                _uiState.update {
                    it.copy(
                        movie = movie,
                        isLoading = false,
                        isFavorite = favoritesManager.isFavorite(movieId)
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Unknown error")
                }
            }
        }
    }

    private fun toggleFavorite(movieId: Int) {
        favoritesManager.toggleFavorite(movieId)
        _uiState.update {
            it.copy(isFavorite = favoritesManager.isFavorite(movieId))
        }
    }
}