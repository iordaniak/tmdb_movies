package com.example.tmdbmovies.ui.movies.list.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tmdbmovies.ui.movies.base.EmptyListMessage
import com.example.tmdbmovies.ui.movies.base.ErrorMessage
import com.example.tmdbmovies.ui.movies.base.ProgressCircle
import com.example.tmdbmovies.ui.movies.list.FavoritesViewModel
import com.example.tmdbmovies.ui.movies.list.MoviesListViewModel
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import com.example.tmdbmovies.ui.movies.list.model.MoviesListUiState
import com.example.tmdbmovies.ui.theme.YellowStar

@Composable
fun MoviesListScreen(
    viewModel: MoviesListViewModel,
    favoritesViewModel: FavoritesViewModel
){
    val uiState = viewModel.moviesListStateUi

    var showPopup by rememberSaveable { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf(MovieUiModel(id=0,isFavorite=false,language="",overview = "",posterPath = "",releaseDate = "",title = "",voteCount = 0,voteAverage =0.0)) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                        onClick = { /* do something */ }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Outlined.List,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                        onClick = { /* do something */ }
                    ) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MoviesListContent(
            moviesListUiState = uiState,
            onItemClick = {
                uiState.value
                selectedMovie = it
                showPopup = true },
            onLikeClick = {
                favoritesViewModel.getMovieFromComposable(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }

    if(showPopup && selectedMovie.id != 0){
        PopupScreen(
            onClickOutside = {showPopup = false},
            content = { PopupMovieDetails(selectedMovie, onDetailsClick = {viewModel.navigateToDetails(selectedMovie)}) }
        )
    }
}
@Composable
fun MoviesListContent(
    moviesListUiState: State<MoviesListUiState>,
    onItemClick: (MovieUiModel) -> Unit,
    onLikeClick: (MovieUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    when (val state = moviesListUiState.value) { is
        MoviesListUiState.DefaultUiState -> {
            MoviesList(moviesList = state.moviesList, onItemClick = { movie -> onItemClick(movie) }, onLikeClick = { movie -> onLikeClick(movie)},modifier = modifier)
        }
        MoviesListUiState.EmptyUiState -> {
            EmptyListMessage("No Movies to display")
        }
        MoviesListUiState.ErrorUiState -> {
            ErrorMessage("Error. Something went wrong :(")
        }
        MoviesListUiState.LoadingUiState -> {
            ProgressCircle()
        }
    }
}

@Composable
fun MoviesList(
    moviesList: List<MovieUiModel>,
    onItemClick: (MovieUiModel) -> Unit,
    onLikeClick: (MovieUiModel) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {items(
            items = moviesList,
            key = { movie -> movie.id }
        ) { movie ->
            MovieItem(
                movieUiModel = movie,
                onClick = { onItemClick(movie) },
                onLikeClick = { onLikeClick(movie) }
            )
        }
    }
}

@Composable
fun MovieItem(
    movieUiModel: MovieUiModel,
    onClick: () -> Unit,
    onLikeClick: () -> Unit
){
    var checked:Boolean by remember { mutableStateOf(movieUiModel.isFavorite) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = movieUiModel.posterPath,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .fillMaxWidth(0.2f)
                .aspectRatio(2f / 3f)
                .clip(shape = RoundedCornerShape(4.dp)),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = movieUiModel.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movieUiModel.overview,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row{
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = YellowStar,
                )
                Text(
                    text = movieUiModel.voteAverage.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 6.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconToggleButton(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onLikeClick()
                }
            ) {
                val tint by animateColorAsState(
                    if (checked) Color.Red
                    else Color.LightGray, label = "DefaultIconToggleButton"
                )
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = tint
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview(){
    MovieItem(
        movieUiModel = MovieUiModel(
            id = 1,
            language = "EN",
            title = "12 Angry Men",
            overview = "The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.",
            posterPath = "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/ppd84D2i9W8jXmsyInGyihiSyqz.jpg",
            releaseDate = "SSS",
            voteAverage = 7.62,
            voteCount = 60,
            isFavorite = false
        ),
        onClick = {},
        onLikeClick = {}
    )
}

