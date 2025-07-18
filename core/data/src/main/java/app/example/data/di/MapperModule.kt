package app.example.data.di

import app.example.data.mapper.DateFormatter
import app.example.data.mapper.MovieDetailsMapper
import app.example.data.mapper.MovieMapper
import app.example.data.mapper.ReviewMapper
import app.example.data.mapper.SimilarMoviesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideDateFormatter(): DateFormatter = DateFormatter()

    @Provides
    @Singleton
    fun provideMovieMapper(dateFormatter: DateFormatter): MovieMapper {
        return MovieMapper(dateFormatter)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsMapper(dateFormatter: DateFormatter): MovieDetailsMapper {
        return MovieDetailsMapper(dateFormatter)
    }

    @Provides
    @Singleton
    fun provideReviewMapper(): ReviewMapper {
        return ReviewMapper()
    }

    @Provides
    @Singleton
    fun provideSimilarMoviesMapper(): SimilarMoviesMapper {
        return SimilarMoviesMapper()
    }
}