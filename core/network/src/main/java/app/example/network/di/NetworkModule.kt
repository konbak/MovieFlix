package app.example.network.di

import app.example.network.service.MovieDetailsService
import app.example.network.service.PopularService
import app.example.network.service.ReviewsService
import app.example.network.service.SimilarMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/"
    private const val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MGVhNDE2YjdjZjdhM2FkNTllYTBjY2E3YTYzNjNmNCIsIm5iZiI6MTc0NzQwOTIxOS43ODQsInN1YiI6IjY4Mjc1OTQzOTI5NDM0NzkzMzBhODY2MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ml2RlATNCi1d2lE_Sf1MHtOQnwqzex-CCxWmsZQZ1J8"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePopularService(retrofit: Retrofit): PopularService {
        return retrofit.create(PopularService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsService(retrofit: Retrofit): MovieDetailsService {
        return retrofit.create(MovieDetailsService::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewsService(retrofit: Retrofit): ReviewsService {
        return retrofit.create(ReviewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideSimilarMoviesService(retrofit: Retrofit): SimilarMoviesService {
        return retrofit.create(SimilarMoviesService::class.java)
    }
}