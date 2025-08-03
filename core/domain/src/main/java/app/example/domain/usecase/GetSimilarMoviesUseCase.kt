package app.example.domain.usecase

import app.example.domain.model.SimilarMovieDomain
import app.example.domain.repository.SimilarMoviesRepository
import app.example.domain.shared.Result
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val similarMoviesRepository: SimilarMoviesRepository
) {
    suspend operator fun invoke(movieId: Int): Result<List<SimilarMovieDomain>> =
        similarMoviesRepository.getSimilarMovies(movieId)
}