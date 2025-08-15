package app.example.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import app.example.designsystem.components.MovieCard
import app.example.domain.model.MovieDomain
import app.example.domain.navigation.MovieFlixScreens
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    //navController: NavController,
    onMovieSelected: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val movies = state.movies.collectAsLazyPagingItems()
    val isRefreshing = movies.loadState.refresh is LoadState.Loading
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.snackbarMessages.collect { event ->
            val message = when (event) {
                is UiEvent.FavoriteStatusMessage -> {
                    if (event.added) {
                        context.getString(R.string.added_to_favorites, event.movieTitle)
                    } else {
                        context.getString(R.string.removed_from_favorites, event.movieTitle)
                    }
                }
            }

            snackbarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetails -> onMovieSelected(effect.movieId)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { paddingValues ->
            HomeStateLessScreen(
                modifier = Modifier.padding(paddingValues),
                isRefreshing = isRefreshing,
                movies = movies,
                favoriteIds = state.favoriteIds,
                onRefresh = {
                    viewModel.onIntent(HomeIntent.Refresh)
                    movies.refresh()
                },
                onFavoriteClick = { movie ->
                    viewModel.onIntent(HomeIntent.ToggleFavorite(movie))
                },
                onMovieSelected = { movieId ->
                    viewModel.onIntent(HomeIntent.ClickMovie(movieId))
                    //navController.navigate(MovieFlixScreens.DetailsScreen.name +"/"+movieId)
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeStateLessScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    movies: LazyPagingItems<MovieDomain>,
    favoriteIds: Set<Int>,
    onRefresh: () -> Unit,
    onFavoriteClick: (MovieDomain) -> Unit = {},
    onMovieSelected: (Int) -> Unit = {}
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(movies.itemCount) {
                val movie = movies[it]
                val isFavorite = movie?.id?.let { id -> favoriteIds.contains(id) } == true

                MovieCard(
                    imageUrl = movie?.posterPath.orEmpty(),
                    title = movie?.title.orEmpty(),
                    voteAverage = movie?.voteAverage ?: 0.0,
                    releaseDate = movie?.releaseDate.orEmpty(),
                    isFavorite = isFavorite,
                    onFavoriteClick = { movie?.let { movieId -> onFavoriteClick(movieId) } },
                    onClick = { movie?.let { movieId -> onMovieSelected(movieId.id) } }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeStateLessScreenPreview() {
    val sampleMovies = listOf(
        MovieDomain(
            id = 1,
            posterPath = "https://image.tmdb.org/t/p/w500/sample1.jpg",
            title = "Sample Movie 1",
            voteAverage = 7.8,
            releaseDate = "2024-01-01"
        ),
        MovieDomain(
            id = 2,
            posterPath = "https://image.tmdb.org/t/p/w500/sample2.jpg",
            title = "Sample Movie 2",
            voteAverage = 8.3,
            releaseDate = "2023-12-15"
        ),
        MovieDomain(
            id = 3,
            posterPath = "https://image.tmdb.org/t/p/w500/sample3.jpg",
            title = "Sample Movie 3",
            voteAverage = 6.9,
            releaseDate = "2023-11-05"
        )
    )

    val pagingFlow = remember {
        flowOf(PagingData.from(sampleMovies))
    }

    val lazyPagingItems = pagingFlow.collectAsLazyPagingItems()

    HomeStateLessScreen(
        isRefreshing = false,
        onRefresh = {},
        movies = lazyPagingItems,
        modifier = Modifier.fillMaxSize(),
        favoriteIds = emptySet()
    )
}
