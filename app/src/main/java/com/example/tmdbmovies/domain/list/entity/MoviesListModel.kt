package com.example.tmdbmovies.domain.list.entity

sealed class MoviesListResult {
    class SuccessResult(val moviesList: List<MovieModel>) : MoviesListResult()
    object EmptyResult : MoviesListResult()
    object ErrorResult : MoviesListResult()
}

data class MovieModel(
    val id: Int,
    val language: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean
)

