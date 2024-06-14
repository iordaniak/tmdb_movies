package com.example.tmdbmovies.ui.movies.list.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tmdbmovies.ui.movies.base.EmptyListMessage
import com.example.tmdbmovies.ui.movies.list.MoviesListViewModel
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import com.example.tmdbmovies.ui.movies.list.navigation.Screens
import com.example.tmdbmovies.ui.movies.list.navigation.listOfNavItems


@Composable
fun MoviesListScreen(
    viewModel: MoviesListViewModel
){
    val navController = rememberNavController()

    val uiState = viewModel.moviesListStateUi

    val favoritesUiState = viewModel.favoriteMovies.observeAsState(emptyList())
    val favoritesConverted = viewModel.movieModelListToUiModelList(favoritesUiState.value)


    var showPopup by rememberSaveable { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf(MovieUiModel(id=0,isFavorite=false,language="",overview = "",posterPath = "",releaseDate = "",originalTitle = "",voteCount = 0,voteAverage =0.0)) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOfNavItems.forEach{ navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } ==true,
                        onClick = {
                            navController.navigate(navItem.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop=true
                                restoreState=true
                            } },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                        label ={ Text(text = navItem.label) }
                    )
                }
            }

        }
    ) {padding ->
        NavHost(
            navController = navController,
            startDestination = Screens.PopularMoviesScreen.name,
            modifier = Modifier.padding(padding)
        ) {
            composable(route = Screens.PopularMoviesScreen.name){
                MoviesListContent(
                    moviesListUiState = uiState,
                    onItemClick = {
                        selectedMovie = it
                        showPopup = true },
                    onLikeClick = {
                        //viewModel.getMovieFromComposable(it)
                        viewModel.updateFavorite(movieToUpdate = it)
                    }
                )
            }
            composable(route = Screens.FavoriteMoviesScreen.name){
                if (favoritesConverted.isEmpty()){
                    EmptyListMessage(message = "No favorites yet")
                } else{
                    MoviesList(
                        moviesList = favoritesConverted,
                        onItemClick = {
                            selectedMovie = it
                            showPopup = true },
                        onLikeClick = {
                            //viewModel.getMovieFromComposable(it)
                            viewModel.updateFavorite(movieToUpdate = it)
                        }
                    )
                }
            }
        }
    }

    if(showPopup && selectedMovie.id != 0){
        PopupScreen(
            onClickOutside = {showPopup = false},
            content = { PopupMovieDetails(selectedMovie, onDetailsClick = {viewModel.navigateToDetails(selectedMovie)}) }
        )
    }
}

