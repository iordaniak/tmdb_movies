package com.example.tmdbmovies.ui.movies.list.mapper

import com.example.tmdbmovies.domain.list.entity.MovieModel
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import javax.inject.Inject

class MoviesListStateUiMapper @Inject constructor() {
    operator fun invoke(moviesList: List<MovieModel>): List<MovieUiModel> {
        return moviesList.map {
            MovieUiModel(
                id = it.id,
                language = it.language,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    }
}

