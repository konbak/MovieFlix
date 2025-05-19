package app.example.domain.usecase

import androidx.paging.PagingData
import app.example.domain.model.MovieDomain
import app.example.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<MovieDomain>> {
        return repository.getPopularMovies()
    }
}