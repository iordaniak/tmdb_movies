package com.example.tmdbmovies.ui.model

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val rating: Double,
    val imageUrl: String
)
