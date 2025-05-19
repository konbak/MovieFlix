package app.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.example.data.mapper.MovieMapper
import app.example.domain.model.MovieDomain
import app.example.domain.repository.MoviesRepository
import app.example.network.service.PopularService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val service: PopularService,
    private val movieMapper: MovieMapper
) : MoviesRepository {
    override fun getPopularMovies(): Flow<PagingData<MovieDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service, movieMapper)
            }
        ).flow
    }
}