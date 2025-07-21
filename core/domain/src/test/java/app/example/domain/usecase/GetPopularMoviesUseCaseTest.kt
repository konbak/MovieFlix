package app.example.domain.usecase

import androidx.paging.PagingData
import app.example.domain.model.MovieDomain
import app.example.domain.repository.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertIs

class GetPopularMoviesUseCaseTest {

    private lateinit var repository: MoviesRepository
    private lateinit var useCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = mockk()
        useCase = GetPopularMoviesUseCase(repository)
    }

    private fun dummyMovie(
        id: Int = 0,
        title: String = "Dummy Title",
        voteAverage: Double = 0.0,
        posterPath: String? = null,
        releaseDate: String = "1970-01-01"
    ) = MovieDomain(id, title, voteAverage, posterPath, releaseDate)

    @Test
    fun `invoke should return PagingData from repository`() = runTest {
        // Given
        val movies = listOf(
            dummyMovie(id = 1, title = "Movie A"),
            dummyMovie(id = 2, title = "Movie B")
        )
        val fakePagingData = PagingData.from(movies)

        coEvery { repository.getPopularMovies() } returns flowOf(fakePagingData)

        // When
        val result = useCase().first()

        // Then
        assertIs<PagingData<MovieDomain>>(result)
        coVerify(exactly = 1) { repository.getPopularMovies() }
    }
}