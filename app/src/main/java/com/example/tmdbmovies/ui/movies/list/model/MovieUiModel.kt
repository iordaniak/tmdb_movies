package com.example.tmdbmovies.ui.movies.list.model


sealed class MoviesListUiState {
    class DefaultUiState(val moviesList: List<MovieUiModel>) : MoviesListUiState()
    object EmptyUiState : MoviesListUiState()
    object ErrorUiState : MoviesListUiState()
    object LoadingUiState : MoviesListUiState()
}


data class MovieUiModel(
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
