package app.example.data.di

import app.example.data.mapper.ImageBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkConfigModule {

    @Provides
    @ImageBaseUrl
    fun provideImageBaseUrl(): String = "https://image.tmdb.org/t/p/w500"
}