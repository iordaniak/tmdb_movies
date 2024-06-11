package com.example.tmdbmovies.ui.movies.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovies.data.repository.TmdbRepository
import com.example.tmdbmovies.domain.list.entity.MoviesListResult
import com.example.tmdbmovies.ui.movies.list.mapper.MoviesListStateUiMapper
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import com.example.tmdbmovies.ui.movies.list.model.MoviesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesListStateUiMapper: MoviesListStateUiMapper,
    private val tmdbRepository: TmdbRepository
): ViewModel() {

    private val _moviesListStateUi: MutableState<MoviesListUiState> = mutableStateOf(MoviesListUiState.LoadingUiState)
    val moviesListStateUi: State<MoviesListUiState> = _moviesListStateUi

    //LiveData for navigation
    private val _selectedMovieId = MutableLiveData<Int>()
    val selectedMovieId: LiveData<Int> = _selectedMovieId

    fun initMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesListStateUi.value = MoviesListUiState.LoadingUiState
            delay(1500)
            fetchPopularMovies()
        }
    }
    private fun fetchPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val moviesListResult = tmdbRepository.getPopularMovies(1)){
                is MoviesListResult.SuccessResult -> {
                    val moviesCatalogUiList = moviesListStateUiMapper(moviesList = moviesListResult.moviesList)
                    _moviesListStateUi.value = MoviesListUiState.DefaultUiState(moviesCatalogUiList)
                }
                MoviesListResult.EmptyResult -> {
                    _moviesListStateUi.value = MoviesListUiState.EmptyUiState
                }
                MoviesListResult.ErrorResult -> {
                    _moviesListStateUi.value = MoviesListUiState.ErrorUiState
                }
            }
        }
    }

    fun navigateToDetails(movie: MovieUiModel){
        _selectedMovieId.value = movie.id
    }
}

