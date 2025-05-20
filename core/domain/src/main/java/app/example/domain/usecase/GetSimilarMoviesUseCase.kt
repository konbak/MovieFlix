package app.example.domain.usecase

import app.example.domain.model.SimilarMovieDomain
import app.example.domain.repository.SimilarMoviesRepository
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val similarMoviesRepository: SimilarMoviesRepository
) {
    suspend operator fun invoke(movieId: Int): List<SimilarMovieDomain> {
        return similarMoviesRepository.getSimilarMovies(movieId)
    }
}