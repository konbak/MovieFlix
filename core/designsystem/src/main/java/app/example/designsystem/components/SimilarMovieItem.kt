package app.example.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SimilarMovieItem(
    posterUrl: String,
    movieId: Int,
    modifier: Modifier = Modifier,
) {
    val posterWidth = 60.dp
    val posterHeight = 100.dp

    Box(
        modifier = modifier
            .size(width = posterWidth, height = posterHeight)
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = "Poster of movie ID $movieId",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SimilarMovieItemPreview() {
    SimilarMovieItem(
        posterUrl = "https://image.tmdb.org/t/p/w500/5LrI4GiCSrChgkdskVZiwv643Kg.jpg",
        movieId = 9659
    )
}