package com.example.tmdbmovies.ui.movies.details.model

import com.example.tmdbmovies.data.details.model.Genre

sealed class MovieDetailsUiState {
    class DefaultUiState(val movieDetailsItem: MovieDetailsUiModel) : MovieDetailsUiState()
    object ErrorUiState : MovieDetailsUiState()
    object LoadingUiState : MovieDetailsUiState()
}

data class MovieDetailsUiModel(
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
