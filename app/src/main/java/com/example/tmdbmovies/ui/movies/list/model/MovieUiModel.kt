package com.example.tmdbmovies.ui.movies.list.model


sealed class MoviesListUiState {
    class DefaultUiState(val moviesList: List<MovieUiModel>) : MoviesListUiState()
    data object EmptyUiState : MoviesListUiState()
    data object ErrorUiState : MoviesListUiState()
    data object LoadingUiState : MoviesListUiState()
}


data class MovieUiModel(
    val id: Int,
    val language: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    var isFavorite: Boolean
)
