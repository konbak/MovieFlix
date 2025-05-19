package app.example.network.service

import app.example.network.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): MoviesResponse
}