package app.example.data.mapper

import androidx.core.text.HtmlCompat
import app.example.domain.model.ReviewDomain
import app.example.network.model.Review

class ReviewMapper {

    fun mapToDomain(review: Review): ReviewDomain {
        return ReviewDomain(
            author = review.author,
            content = stripHtml(review.content)
        )
    }

    fun mapToDomainList(reviews: List<Review>): List<ReviewDomain> {
        return reviews
            .take(3)
            .map { mapToDomain(it) }
    }

    private fun stripHtml(input: String): String {
        return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}