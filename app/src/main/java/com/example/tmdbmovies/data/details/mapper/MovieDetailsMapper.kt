package com.example.tmdbmovies.data.details.mapper

import com.example.tmdbmovies.data.details.model.RemoteMovieDetailsResponse
import com.example.tmdbmovies.domain.details.entity.MovieDetailsModel
import com.example.tmdbmovies.domain.details.entity.MovieDetailsResult
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {
    operator fun invoke(remoteMovieDetailsResponse: RemoteMovieDetailsResponse?): MovieDetailsResult {
        if (remoteMovieDetailsResponse != null) {
            val movieDetailsModel = MovieDetailsModel(
                backdropPath = "https://image.tmdb.org/t/p/w533_and_h300_bestv2"+remoteMovieDetailsResponse.backdropPath,
                genres = remoteMovieDetailsResponse.genres,
                id = remoteMovieDetailsResponse.id,
                originCountry = remoteMovieDetailsResponse.originCountry,
                originalLanguage = remoteMovieDetailsResponse.originalLanguage,
                originalTitle = remoteMovieDetailsResponse.originalTitle,
                overview = remoteMovieDetailsResponse.overview,
                popularity = remoteMovieDetailsResponse.popularity,
                posterPath = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2"+ remoteMovieDetailsResponse.posterPath,
                releaseDate = remoteMovieDetailsResponse.releaseDate,
                runtime = remoteMovieDetailsResponse.runtime,
                tagline = remoteMovieDetailsResponse.tagline,
                voteAverage = remoteMovieDetailsResponse.voteAverage,
                voteCount = remoteMovieDetailsResponse.voteCount
            )
            return MovieDetailsResult.SuccessResult(movieDetailsModel = movieDetailsModel)
        }
        return MovieDetailsResult.ErrorResult
    }
}
