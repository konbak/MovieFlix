package app.example.details

import app.example.domain.model.MovieDetailsDomain
import app.example.domain.shared.DomainError
import app.example.domain.shared.FavoritesManager
import app.example.domain.usecase.GetMovieDetailsUseCase
import app.example.domain.usecase.GetReviewsUseCase
import app.example.domain.usecase.GetSimilarMoviesUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import app.example.domain.shared.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    private val getReviewsUseCase: GetReviewsUseCase = mockk()
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase = mockk()
    private val favoritesManager: FavoritesManager = mockk(relaxed = true)

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            getReviewsUseCase = getReviewsUseCase,
            getSimilarMoviesUseCase = getSimilarMoviesUseCase,
            favoritesManager = favoritesManager
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadMovie returns Success and updates uiState correctly`() = runTest {
        val movieId = 1
        val fakeMovie = MovieDetailsDomain(
            title = "Batman",
            voteAverage = 8.2,
            posterUrl = "https://example.com/poster.jpg",
            homepage = "https://batman.com",
            overview = "Gotham's dark knight.",
            genres = listOf("Action", "Adventure"),
            releaseDate = "2023-12-01",
            runtime = "150m"
        )

        coEvery { getMovieDetailsUseCase(movieId) } returns Result.Success(fakeMovie)
        every { favoritesManager.isFavorite(movieId) } returns true

        viewModel.onEvent(DetailsEvent.LoadMovieDetails(movieId))

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals(fakeMovie, state.movie)
        assertTrue(state.isFavorite)
    }

    @Test
    fun `loadMovie returns Error and updates uiState with error`() = runTest {
        val movieId = 1
        val error = DomainError.Network

        coEvery { getMovieDetailsUseCase(movieId) } returns Result.Error(error)

        viewModel.onEvent(DetailsEvent.LoadMovieDetails(movieId))

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertNull(state.movie)
        assertEquals(error, state.error) // ή αν έχεις κάποιο message, άλλαξέ το ανάλογα
    }
}
