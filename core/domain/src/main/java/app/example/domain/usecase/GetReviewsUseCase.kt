package app.example.domain.usecase

import app.example.domain.model.ReviewDomain
import app.example.domain.repository.ReviewsRepository
import app.example.domain.shared.Result
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) {
    suspend operator fun invoke(movieId: Int): Result<List<ReviewDomain>> =
        reviewsRepository.getReviews(movieId)
}