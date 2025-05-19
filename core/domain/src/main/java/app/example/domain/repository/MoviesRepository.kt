package app.example.domain.repository

import androidx.paging.PagingData
import app.example.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<PagingData<MovieDomain>>
}