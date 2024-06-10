package com.example.tmdbmovies.data.list.mapper

import com.example.tmdbmovies.data.list.model.RemoteMoviesListResponse
import com.example.tmdbmovies.domain.list.entity.MovieModel
import com.example.tmdbmovies.domain.list.entity.MoviesListResult
import javax.inject.Inject

class MoviesListMapper @Inject constructor() {
    operator fun invoke(remoteMoviesListResponse: RemoteMoviesListResponse?): MoviesListResult {

        if (remoteMoviesListResponse != null) {
            if(remoteMoviesListResponse.results == null) return MoviesListResult.ErrorResult

            if (remoteMoviesListResponse.results.isEmpty()){
                return MoviesListResult.EmptyResult
            }
            else{
                val moviesCatalogList = remoteMoviesListResponse.results.mapNotNull {
                    if(it?.id == null) return@mapNotNull null

                    MovieModel(
                        id = it.id,
                        language = it.originalLanguage,
                        title = it.originalTitle,
                        overview = it.overview,
                        posterPath = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2" + it.posterPath,
                        releaseDate = it.releaseDate,
                        voteAverage = it.voteAverage,
                        voteCount = it.voteCount,
                        isFavorite = false
                    )
                }
                return MoviesListResult.SuccessResult(moviesList = moviesCatalogList)
            }
        }
        return MoviesListResult.ErrorResult
    }
}