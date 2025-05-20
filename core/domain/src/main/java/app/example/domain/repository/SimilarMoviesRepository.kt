package app.example.domain.repository

import app.example.domain.model.SimilarMovieDomain

interface SimilarMoviesRepository {
    suspend fun getSimilarMovies(movieId: Int): List<SimilarMovieDomain>
}