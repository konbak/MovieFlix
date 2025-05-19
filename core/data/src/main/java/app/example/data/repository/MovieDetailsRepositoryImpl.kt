package app.example.data.repository

import app.example.data.mapper.MovieDetailsMapper
import app.example.domain.model.MovieDetailsDomain
import app.example.domain.repository.MovieDetailsRepository
import app.example.network.service.MovieDetailsService
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val service: MovieDetailsService,
    private val mapper: MovieDetailsMapper
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDomain {
        return mapper.mapToDomain(service.getMovieDetails(movieId))
    }
}