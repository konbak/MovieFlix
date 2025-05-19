package app.example.domain.usecase

import app.example.domain.model.MovieDetailsDomain
import app.example.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int): MovieDetailsDomain {
        return repository.getMovieDetails(movieId)
    }
}