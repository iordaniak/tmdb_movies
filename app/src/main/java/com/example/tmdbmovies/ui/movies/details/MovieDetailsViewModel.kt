package com.example.tmdbmovies.ui.movies.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovies.data.repository.TmdbRepository
import com.example.tmdbmovies.domain.details.entity.MovieDetailsResult
import com.example.tmdbmovies.ui.movies.details.mapper.MovieDetailsStateUiMapper
import com.example.tmdbmovies.ui.movies.details.model.MovieDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsStateUiMapper: MovieDetailsStateUiMapper,
    private val tmdbRepository: TmdbRepository

): ViewModel() {

    private val _movieDetailsStateUi: MutableState<MovieDetailsUiState> = mutableStateOf(
        MovieDetailsUiState.LoadingUiState)
    val movieDetailsStateUi: State<MovieDetailsUiState> = _movieDetailsStateUi

    fun initMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetailsStateUi.value = MovieDetailsUiState.LoadingUiState
            delay(1500)
            fetchMovieDetails(movieId)
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val movieDetailsResult = tmdbRepository.getMovieDetails(movieId)){ is
                MovieDetailsResult.SuccessResult -> {
                    val movieDetailsUi = movieDetailsStateUiMapper(movieDetailsModel = movieDetailsResult.movieDetailsModel)
                    _movieDetailsStateUi.value = MovieDetailsUiState.DefaultUiState(movieDetailsUi)
                }
                MovieDetailsResult.ErrorResult -> {
                    _movieDetailsStateUi.value = MovieDetailsUiState.ErrorUiState
                }
            }
        }
    }
}