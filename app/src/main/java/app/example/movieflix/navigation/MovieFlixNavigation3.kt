package app.example.movieflix.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import app.example.details.DetailsScreen
import app.example.details.DetailsViewModel
import app.example.home.HomeScreen
import app.example.home.HomeViewModel

@Composable
fun MovieFlixNavigation3() {
    val backStack = rememberNavBackStack(Home)

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavDisplay(backStack) { route ->
                when (route) {
                    is Home -> NavEntry(route) {
                        val homeViewModel = hiltViewModel<HomeViewModel>()
                        HomeScreen(
                            viewModel = homeViewModel,
                            onMovieSelected = { movieId ->
                                backStack.add(Details(movieId))
                            }
                        )
                    }

                    is Details -> NavEntry(route) {
                        val detailsViewModel = hiltViewModel<DetailsViewModel>()
                        DetailsScreen(
                            movieId = route.movieId,
                            viewModel = detailsViewModel,
                            onBack = {
                                backStack.removeLastOrNull()
                            }
                        )
                    }

                    else -> NavEntry(route) {
                        Text("Unknown screen")
                    }
                }
            }
        }
    }
}