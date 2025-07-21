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

    private fun applyResult(result: DetailsResult) {
        _uiState.update { currentState ->
            reduce(currentState, result)
        }
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            applyResult(DetailsResult.Loading)
            try {
                val movie = getMovieDetailsUseCase(movieId)
                val isFavorite = favoritesManager.isFavorite(movieId)
                applyResult(DetailsResult.MovieLoaded(movie, isFavorite))
            } catch (e: Exception) {
                applyResult(DetailsResult.MovieLoadError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun loadReviews(movieId: Int) {
        viewModelScope.launch {
            try {
                val reviews = getReviewsUseCase(movieId)
                applyResult(DetailsResult.ReviewsLoaded(reviews))
            } catch (e: Exception) {
                applyResult(DetailsResult.ReviewsLoadError(e.message ?: "Error loading reviews"))
            }
        }
    }

    private fun loadSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            try {
                val similarMovies = getSimilarMoviesUseCase(movieId)
                applyResult(DetailsResult.SimilarMoviesLoaded(similarMovies))
            } catch (e: Exception) {
                applyResult(DetailsResult.SimilarMoviesLoadError(e.message ?: "Error loading similar movies"))
            }
        }
    }

    private fun toggleFavorite(movieId: Int) {
        favoritesManager.toggleFavorite(movieId)
        val isFavorite = favoritesManager.isFavorite(movieId)
        applyResult(DetailsResult.FavoriteToggled(isFavorite))
    }
}