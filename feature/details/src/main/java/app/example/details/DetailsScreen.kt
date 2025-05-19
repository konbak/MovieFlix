package app.example.details

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.example.designsystem.components.MovieHeaderSection
import app.example.designsystem.components.RatingStars
import app.example.domain.model.MovieDetailsDomain

@Composable
fun DetailsScreen(
    movieId: Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(movieId) {
        viewModel.onEvent(DetailsEvent.LoadMovieDetails(movieId))
    }

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        content = { paddingValues ->
            DetailsStateLessScreen(
                modifier = Modifier.padding(paddingValues),
                movie = uiState.value.movie,
                isFavorite = uiState.value.isFavorite,
                onFavoriteClick = { viewModel.onEvent(DetailsEvent.ToggleFavorite(movieId)) },
                onShareClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, uiState.value.movie?.homepage)
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                },
            )
        }
    )
}

@Composable
internal fun DetailsStateLessScreen(
    modifier: Modifier = Modifier,
    movie: MovieDetailsDomain?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) {
    movie?.let {
        Column(modifier = modifier.fillMaxSize()) {
            MovieHeaderSection(
                posterUrl = it.posterUrl,
                title = it.title,
                genresList = it.genres,
                isFavorite = isFavorite,
                showShareButton = it.homepage?.isNotBlank() == true,
                onToggleFavorite = onFavoriteClick,
                onShareClick = onShareClick,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = modifier) {
                Text(
                    text = it.releaseDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                RatingStars(it.voteAverage)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = it.runtime,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}