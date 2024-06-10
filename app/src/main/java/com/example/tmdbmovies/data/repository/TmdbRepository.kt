package com.example.tmdbmovies.data.repository

import com.example.tmdbmovies.data.list.mapper.MoviesListMapper
import com.example.tmdbmovies.domain.list.entity.MoviesListResult
import com.example.tmdbmovies.framework.tmdb.datasource.RetrofitInstance
import javax.inject.Inject

class TmdbRepository @Inject constructor(
    private val moviesListMapper: MoviesListMapper
){
    private val api = RetrofitInstance.tmdbApi

    suspend fun getPopularMovies(page: Int): MoviesListResult {
        val remoteResponse = api.getMoviesList("98fe882937eb4b0b4ab92ebe3897d962",page)
        return moviesListMapper(remoteResponse)
    }
}