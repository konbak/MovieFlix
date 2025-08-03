package app.example.data.repository

import app.example.data.mapper.MovieDetailsMapper
import app.example.domain.model.MovieDetailsDomain
import app.example.domain.repository.MovieDetailsRepository
import app.example.network.service.MovieDetailsService
import app.example.domain.shared.Result
import app.example.network.toDomainError
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val service: MovieDetailsService,
    private val mapper: MovieDetailsMapper
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain> {
        return try {
            val response = service.getMovieDetails(movieId)
            Result.Success(mapper.mapToDomain(response))
        } catch (e: Exception) {
            Result.Error(e.toDomainError())
        }
    }
}