package app.example.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.domain.shared.FavoritesManager
import app.example.domain.usecase.GetMovieDetailsUseCase
import app.example.domain.usecase.GetReviewsUseCase
import app.example.domain.usecase.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getReviewsUseCase: GetReviewsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val favoritesManager: FavoritesManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.LoadMovieDetails -> loadMovie(event.movieId)
            is DetailsEvent.LoadMovieReviews -> loadReviews(event.movieId)
            is DetailsEvent.LoadSimilarMovies -> loadSimilarMovies(event.movieId)
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

    private fun loadReviews(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(reviews = emptyList(), reviewsError = null) }

            try {
                val reviews = getReviewsUseCase(movieId)
                _uiState.update { it.copy(reviews = reviews) }
            } catch (e: Exception) {
                _uiState.update { it.copy(reviewsError = e.message ?: "Unknown error loading reviews") }
            }
        }
    }

    private fun loadSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(similarMovies = emptyList(), similarMoviesError = null) }

            try {
                val similarMovies = getSimilarMoviesUseCase(movieId)
                _uiState.update { it.copy(similarMovies = similarMovies) }
            } catch (e: Exception) {
                _uiState.update { it.copy(similarMoviesError = e.message ?: "Unknown error loading similar movies") }
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