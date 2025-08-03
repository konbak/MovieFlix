package app.example.data.repository

import app.example.data.mapper.SimilarMoviesMapper
import app.example.domain.model.SimilarMovieDomain
import app.example.domain.repository.SimilarMoviesRepository
import app.example.network.service.SimilarMoviesService
import app.example.domain.shared.Result
import app.example.network.toDomainError
import javax.inject.Inject

class SimilarMoviesRepositoryImpl @Inject constructor(
    private val similarMoviesService: SimilarMoviesService,
    private val similarMoviesMapper: SimilarMoviesMapper,
) : SimilarMoviesRepository {
    override suspend fun getSimilarMovies(movieId: Int): Result<List<SimilarMovieDomain>> {
        return try {
            val response = similarMoviesService.getSimilarMovies(movieId)
            Result.Success(similarMoviesMapper.mapToDomainList(response.results))
        } catch (e: Exception) {
            Result.Error(e.toDomainError())
        }
    }
}