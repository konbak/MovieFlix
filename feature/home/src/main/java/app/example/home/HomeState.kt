package app.example.home

import androidx.paging.PagingData
import app.example.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val movies: Flow<PagingData<MovieDomain>>,
    val favoriteIds: Set<Int> = emptySet()
)