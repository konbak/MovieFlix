package app.example.domain.repository

import app.example.domain.model.ReviewDomain

interface ReviewsRepository {
    suspend fun getReviews(movieId: Int): List<ReviewDomain>
}