package app.example.data.repository

import app.example.data.mapper.SimilarMoviesMapper
import app.example.domain.model.SimilarMovieDomain
import app.example.domain.repository.SimilarMoviesRepository
import app.example.network.service.SimilarMoviesService
import javax.inject.Inject

class SimilarMoviesRepositoryImpl @Inject constructor(
    private val similarMoviesService: SimilarMoviesService,
    private val similarMoviesMapper: SimilarMoviesMapper,
) : SimilarMoviesRepository {

    override suspend fun getSimilarMovies(movieId: Int): List<SimilarMovieDomain> {
        val response = similarMoviesService.getSimilarMovies(movieId = movieId)
        return similarMoviesMapper.mapToDomainList(response.results)
    }
}