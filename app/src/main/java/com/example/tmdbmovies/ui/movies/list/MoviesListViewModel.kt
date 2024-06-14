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
import kotlinx.coroutines.withContext
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

    //LiveData for favoritesList
    val favoriteMovies: LiveData<List<MovieModel>> = tmdbRepository.favorites


    fun initMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesListStateUi.value = MoviesListUiState.LoadingUiState
            delay(1000)
            fetchPopularMovies()
        }
    }

    private fun fetchPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val moviesListResult = tmdbRepository.getPopularMovies(1)){
                is MoviesListResult.SuccessResult -> {
                    val popularMoviesList = moviesListStateUiMapper(moviesList = moviesListResult.moviesList)
                    val favoriteMoviesList = movieModelListToUiModelList(favoriteMovies.value)
                    val checkedList = mergePopularAndFavoriteMovies(popularMoviesList,favoriteMoviesList)
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

    // This function checks if any of the popular movies are also marked as favorites.
    private fun mergePopularAndFavoriteMovies(
        popularMovies: List<MovieUiModel>,
        favoriteMovies: List<MovieUiModel>
    ): List<MovieUiModel> {
        val favoriteMovieIds = favoriteMovies.map { it.id }.toSet()
        return popularMovies.map { movie ->
            if (movie.id in favoriteMovieIds) {
                movie.copy(isFavorite = true)
            } else {
                movie
            }
        }
    }
    // this function is called when the user clicks a movie's like button and inverts the movie.isFavorite(Boolean) attribute
    // It then inserts/deletes the movie from the Database but also updates the state
    fun updateFavorite(movieToUpdate: MovieUiModel) {
        val currentState = _moviesListStateUi.value
        if (currentState is MoviesListUiState.DefaultUiState) {
            val updatedList = currentState.moviesList.map { movie ->
                if (movie.id == movieToUpdate.id) {
                    movie.copy(isFavorite = !movieToUpdate.isFavorite)
                } else {
                    movie
                }
            }
            _moviesListStateUi.value = MoviesListUiState.DefaultUiState(updatedList)
        }
        if(movieToUpdate.isFavorite) deleteMovie(movieToUpdate)
        else{
            movieToUpdate.isFavorite=true
            insertMovie(movieToUpdate)
        }
    }

    private fun insertMovie(movie: MovieUiModel) = viewModelScope.launch{
        tmdbRepository.insert(moviesListStateUiMapper.convertUiToDomain(movie))
    }
    private fun deleteMovie(movie: MovieUiModel)= viewModelScope.launch{
        tmdbRepository.delete(moviesListStateUiMapper.convertUiToDomain(movie))
    }

    fun movieModelListToUiModelList(movieList: List<MovieModel>?): List<MovieUiModel> {
        if (movieList.isNullOrEmpty()) return emptyList()
        return movieList.map { movie -> moviesListStateUiMapper.convertDomainToUi(movie) }
    }

    fun navigateToDetails(movie: MovieUiModel){
        _selectedMovieId.value = movie.id
    }

    fun doneNavigating() {
        _selectedMovieId.value = 0
    }
}