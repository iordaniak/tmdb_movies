package com.example.tmdbmovies.ui.movies.details

import androidx.lifecycle.ViewModel
import com.example.tmdbmovies.data.repository.TmdbRepository
import com.example.tmdbmovies.ui.movies.list.mapper.MoviesListStateUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(

): ViewModel() {

    fun initMovieDetails(movieId: Int) {

    }

}