package app.example.data.repository

import app.example.data.mapper.ReviewMapper
import app.example.domain.model.ReviewDomain
import app.example.domain.repository.ReviewsRepository
import app.example.network.service.ReviewsService
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val reviewsService: ReviewsService,
    private val reviewMapper: ReviewMapper
) : ReviewsRepository {

    override suspend fun getReviews(movieId: Int): List<ReviewDomain> {
        val response = reviewsService.getMovieReviews(movieId = movieId)
        return reviewMapper.mapToDomainList(response.results)
    }
}