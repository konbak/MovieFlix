package app.example.domain.repository

import app.example.domain.model.MovieDetailsDomain
import app.example.domain.shared.Result

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain>
}