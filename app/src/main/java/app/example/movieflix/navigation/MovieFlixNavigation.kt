package app.example.movieflix.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.example.details.DetailsScreen
import app.example.details.DetailsViewModel
import app.example.domain.navigation.MovieFlixScreens
import app.example.home.HomeScreen
import app.example.home.HomeViewModel

@Composable
fun MovieFlixNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieFlixScreens.HomeScreen.name
    ) {
//        composable(route = MovieFlixScreens.HomeScreen.name) {
//            val homeViewModel = hiltViewModel<HomeViewModel>()
//            HomeScreen(
//                navController = navController,
//                viewModel = homeViewModel,
//            )
//        }
//
//        composable(
//            route = "${MovieFlixScreens.DetailsScreen.name}/{movieId}",
//            arguments = listOf(navArgument("movieId") {
//                type = NavType.IntType
//            })
//        ) { backStackEntry ->
//            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
//            val detailsViewModel = hiltViewModel<DetailsViewModel>()
//            DetailsScreen(
//                movieId = movieId,
//                viewModel = detailsViewModel,
//            )
//        }
    }
}