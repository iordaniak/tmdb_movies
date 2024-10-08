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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import com.example.tmdbmovies.ui.movies.list.model.MoviesListUiState
import com.example.tmdbmovies.ui.theme.YellowStar


@Composable
fun MoviesListContent(
    moviesListUiState: State<MoviesListUiState>,
    onItemClick: (MovieUiModel) -> Unit,
    onLikeClick: (MovieUiModel) -> Unit,
) {
    when (val state = moviesListUiState.value) { is
        MoviesListUiState.DefaultUiState -> {
        MoviesList(
                moviesList = state.moviesList,
                onItemClick = { movie -> onItemClick(movie) },
                onLikeClick = { movie -> onLikeClick(movie) }
            )
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
    onLikeClick: (MovieUiModel) -> Unit
){
    LazyColumn(
        modifier = Modifier
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
                text = movieUiModel.originalTitle,
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
                    text = movieUiModel.voteAverage.toString().take(4),
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
            originalTitle = "12 Angry Men",
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

