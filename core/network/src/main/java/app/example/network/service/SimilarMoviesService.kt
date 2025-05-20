package app.example.network.service

import app.example.network.model.SimilarMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarMoviesService {
    @GET("3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): SimilarMoviesResponse
}