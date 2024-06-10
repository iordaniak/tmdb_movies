package com.example.tmdbmovies.framework.tmdb.api

import com.example.tmdbmovies.data.details.RemoteMovieDetailsResponse
import com.example.tmdbmovies.data.list.model.RemoteMoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("3/movie/popular")
    suspend fun getMoviesList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): RemoteMoviesListResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<RemoteMovieDetailsResponse>
}