package app.example.domain.di

import app.example.domain.repository.MovieDetailsRepository
import app.example.domain.repository.MoviesRepository
import app.example.domain.usecase.GetMovieDetailsUseCase
import app.example.domain.usecase.GetPopularMoviesUseCase
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
}