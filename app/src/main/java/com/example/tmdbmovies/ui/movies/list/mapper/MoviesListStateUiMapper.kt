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
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    }

     fun convertUiToDomain(movieUiModel: MovieUiModel): MovieModel {
        return MovieModel(
            id = movieUiModel.id,
            language = movieUiModel.language,
            originalTitle = movieUiModel.originalTitle,
            overview = movieUiModel.overview,
            posterPath = movieUiModel.posterPath,
            releaseDate = movieUiModel.releaseDate,
            voteAverage = movieUiModel.voteAverage,
            voteCount = movieUiModel.voteCount,
            isFavorite = movieUiModel.isFavorite
        )
    }

     fun convertDomainToUi(movie: MovieModel): MovieUiModel {
        return MovieUiModel(
            movie.id,
            movie.language,
            movie.originalTitle,
            movie.overview,
            movie.posterPath,
            movie.releaseDate,
            movie.voteAverage,
            movie.voteCount,
            movie.isFavorite
        )
    }
}

