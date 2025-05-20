package app.example.domain.di

import app.example.domain.repository.MovieDetailsRepository
import app.example.domain.repository.MoviesRepository
import app.example.domain.repository.ReviewsRepository
import app.example.domain.repository.SimilarMoviesRepository
import app.example.domain.usecase.GetMovieDetailsUseCase
import app.example.domain.usecase.GetPopularMoviesUseCase
import app.example.domain.usecase.GetReviewsUseCase
import app.example.domain.usecase.GetSimilarMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(
        moviesRepository: MoviesRepository
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(moviesRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(
        movieDetailsRepository: MovieDetailsRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(movieDetailsRepository)
    }

    @Provides
    @Singleton
    fun provideGetReviewsUseCase(
        reviewsRepository: ReviewsRepository
    ): GetReviewsUseCase {
        return GetReviewsUseCase(reviewsRepository)
    }

    @Provides
    @Singleton
    fun provideGetSimilarMoviesUseCase(
        similarMoviesRepository: SimilarMoviesRepository
    ): GetSimilarMoviesUseCase {
        return GetSimilarMoviesUseCase(similarMoviesRepository)
    }
}