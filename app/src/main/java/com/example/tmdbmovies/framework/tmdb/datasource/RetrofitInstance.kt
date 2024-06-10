package com.example.tmdbmovies.framework.tmdb.datasource

import com.example.tmdbmovies.framework.tmdb.api.TmdbApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/"
    val tmdbApi: TmdbApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(TmdbApi::class.java)
    }
}