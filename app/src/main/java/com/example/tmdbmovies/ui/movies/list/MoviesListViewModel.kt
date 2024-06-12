package com.example.tmdbmovies.ui.movies.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovies.data.repository.TmdbRepository
import com.example.tmdbmovies.domain.list.entity.MovieModel
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

    // State Ui
    private val _moviesListStateUi: MutableState<MoviesListUiState> = mutableStateOf(MoviesListUiState.LoadingUiState)
    val moviesListStateUi: State<MoviesListUiState> = _moviesListStateUi

    //LiveData for navigation
    private val _selectedMovieId = MutableLiveData(0)
    val selectedMovieId: LiveData<Int> = _selectedMovieId


    private var favoriteList: List<MovieModel> = emptyList()


    fun initMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesListStateUi.value = MoviesListUiState.LoadingUiState
            delay(800)
            fetchPopularMovies()
        }
    }

    private fun fetchPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val moviesListResult = tmdbRepository.getPopularMovies(1)){
                is MoviesListResult.SuccessResult -> {
                    val popularMoviesList = moviesListStateUiMapper(moviesList = moviesListResult.moviesList)
                    val checkedList = checkPopularsForFavorites(popularMoviesList, favoriteList)
                    _moviesListStateUi.value = MoviesListUiState.DefaultUiState(checkedList)
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

    fun giveFavoritesToListViewModel(favorites: List<MovieModel>){
        favoriteList = favorites
    }


    private fun checkPopularsForFavorites(popularList: List<MovieUiModel>, favoriteList: List<MovieModel>): List<MovieUiModel>{
        // Convert favoriteList to a Set for efficient lookups by movie ID from popularList
        val favoriteListIds = favoriteList.map { it.id }.toSet()
        // Filter popularList based on movie ID being present in the set from favoriteList
        return popularList.filter { movie -> favoriteListIds.contains(movie.id) }
    }



    fun navigateToDetails(movie: MovieUiModel){
        _selectedMovieId.value = movie.id
    }

    fun doneNavigating() {
        _selectedMovieId.value = 0
    }
}