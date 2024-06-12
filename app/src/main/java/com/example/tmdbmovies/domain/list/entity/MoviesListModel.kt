package com.example.tmdbmovies.domain.list.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class MoviesListResult {
    class SuccessResult(val moviesList: List<MovieModel>) : MoviesListResult()
    object EmptyResult : MoviesListResult()
    object ErrorResult : MoviesListResult()
}

@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey
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

