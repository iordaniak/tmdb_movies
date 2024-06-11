package com.example.tmdbmovies.domain.details.entity

import com.example.tmdbmovies.data.details.model.Genre

sealed class MovieDetailsResult {
    class SuccessResult(val movieDetailsModel: MovieDetailsModel) : MovieDetailsResult()
    object ErrorResult : MovieDetailsResult()
}

data class MovieDetailsModel(
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int
)
