package com.example.tmdbmovies.ui.movies.details.mapper

import com.example.tmdbmovies.domain.details.entity.MovieDetailsModel
import com.example.tmdbmovies.ui.movies.details.model.MovieDetailsUiModel
import javax.inject.Inject

class MovieDetailsStateUiMapper @Inject constructor() {
    operator fun invoke(movieDetailsModel: MovieDetailsModel): MovieDetailsUiModel {
        return MovieDetailsUiModel(
            backdropPath = movieDetailsModel.backdropPath,
            genres = movieDetailsModel.genres,
            id = movieDetailsModel.id,
            originCountry = movieDetailsModel.originCountry,
            originalLanguage = movieDetailsModel.originalLanguage,
            originalTitle = movieDetailsModel.originalTitle,
            overview = movieDetailsModel.overview,
            popularity = movieDetailsModel.popularity,
            posterPath = movieDetailsModel.posterPath,
            releaseDate = movieDetailsModel.releaseDate,
            runtime = movieDetailsModel.runtime,
            tagline = movieDetailsModel.tagline,
            voteAverage = movieDetailsModel.voteAverage,
            voteCount = movieDetailsModel.voteCount
        )
    }
}