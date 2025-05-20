package app.example.data.di

import app.example.data.repository.ReviewsRepositoryImpl
import app.example.domain.repository.ReviewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReviewsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindReviewsRepository(
        impl: ReviewsRepositoryImpl
    ): ReviewsRepository
}