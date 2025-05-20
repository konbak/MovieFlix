package app.example.data.di

import app.example.data.repository.SimilarMoviesRepositoryImpl
import app.example.domain.repository.SimilarMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SimilarMoviesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSimilarMoviesRepository(
        impl: SimilarMoviesRepositoryImpl
    ): SimilarMoviesRepository
}