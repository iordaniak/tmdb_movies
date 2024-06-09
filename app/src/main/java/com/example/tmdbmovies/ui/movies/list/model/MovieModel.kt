package com.example.tmdbmovies.ui.movies.list.model

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val rating: Double,
    val isFavored: Boolean,
    val imageUrl: String
)
