package app.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.example.data.mapper.MovieMapper
import app.example.domain.model.MovieDomain
import app.example.network.service.PopularService

class MoviesPagingSource(
    private val service: PopularService,
    private val mapper: MovieMapper
) : PagingSource<Int, MovieDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomain> {
        val page = params.key ?: 1

        return try {
            val response = service.getPopularMovies(page = page)
            val movieDomains = response.movies.map { mapper.mapToDomain(it) }

            LoadResult.Page(
                data = movieDomains,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.movies.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }
}