package app.example.domain.usecase

import app.example.domain.model.ReviewDomain
import app.example.domain.repository.ReviewsRepository
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) {
    suspend operator fun invoke(movieId: Int): List<ReviewDomain> {
        return reviewsRepository.getReviews(movieId)
    }
}