package app.example.domain.repository

import app.example.domain.model.ReviewDomain
import app.example.domain.shared.Result

interface ReviewsRepository {
    suspend fun getReviews(movieId: Int): Result<List<ReviewDomain>>
}