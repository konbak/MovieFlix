package app.example.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieHeaderSection(
    posterUrl: String,
    title: String,
    genresList: List<String>,
    isFavorite: Boolean,
    showShareButton: Boolean = false,
    onToggleFavorite: () -> Unit,
    onShareClick: () -> Unit,
) {
    val genres = genresList.joinToString(", ")
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = posterUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            if (showShareButton) {
                IconButton(
                    onClick = onShareClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = genres,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            FavoriteButton(
                isFavorite = isFavorite,
                onClick = onToggleFavorite,
                iconSize = 24.dp,
                tint = if (isFavorite) Color.Red else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieHeaderSection() {
    MovieHeaderSection(
        posterUrl = "https://image.tmdb.org/t/p/w500/your-poster-path.jpg",
        title = "The Lord of the Rings: The Return of the King",
        genresList = listOf("Action, Adventure, Fantasy"),
        isFavorite = true,
        showShareButton = true,
        onToggleFavorite = {},
        onShareClick = {}
    )
}
