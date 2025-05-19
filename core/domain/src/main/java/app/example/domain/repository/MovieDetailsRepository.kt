package app.example.domain.repository

import app.example.domain.model.MovieDetailsDomain

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDomain
}