package app.example.domain.repository

import app.example.domain.model.SimilarMovieDomain
import app.example.domain.shared.Result

interface SimilarMoviesRepository {
    suspend fun getSimilarMovies(movieId: Int): Result<List<SimilarMovieDomain>>
}