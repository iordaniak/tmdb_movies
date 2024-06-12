package com.example.tmdbmovies.ui.movies.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovies.domain.list.entity.MovieModel
import com.example.tmdbmovies.framework.database.FavoritesRepository
import com.example.tmdbmovies.framework.database.MovieDatabase
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoritesViewModel(application: Application): AndroidViewModel(application) {

    val favoriteMovies: LiveData<List<MovieModel>>
    private val repository: FavoritesRepository

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = FavoritesRepository(movieDao)
        favoriteMovies = repository.favorites
    }

    private fun insertMovie(movie: MovieModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(movie)
        }
    }
    private fun deleteMovie(movie: MovieModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(movie)
        }
    }

    fun getMovieFromComposable(movie: MovieUiModel){
        val selectedMovie = convertMovieUiModelToMovieModel(movie)
        if(selectedMovie.isFavorite){
            deleteMovie(selectedMovie)
        }
        else{
            insertMovie(selectedMovie)
        }
    }

    private fun convertMovieUiModelToMovieModel(movieUiModel: MovieUiModel): MovieModel {
        return MovieModel(
            id = movieUiModel.id,
            language = movieUiModel.language,
            title = movieUiModel.title,
            overview = movieUiModel.overview,
            posterPath = movieUiModel.posterPath,
            releaseDate = movieUiModel.releaseDate,
            voteAverage = movieUiModel.voteAverage,
            voteCount = movieUiModel.voteCount,
            isFavorite = movieUiModel.isFavorite
        )
    }
}