package com.example.tmdbmovies.ui.movies.details.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tmdbmovies.ui.movies.base.ErrorMessage
import com.example.tmdbmovies.ui.movies.base.ProgressCircle
import com.example.tmdbmovies.ui.movies.details.MovieDetailsViewModel
import com.example.tmdbmovies.ui.movies.details.model.MovieDetailsUiModel
import com.example.tmdbmovies.ui.movies.details.model.MovieDetailsUiState

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
) {
    val uiState = viewModel.movieDetailsStateUi

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        MovieDetailsContent(uiState)
    }
}

@Composable
fun MovieDetailsContent(
    movieDetailsUiState: State<MovieDetailsUiState>,
) {
    when (val state = movieDetailsUiState.value) { is

        MovieDetailsUiState.DefaultUiState -> {
            MovieDetails(state.movieDetailsItem)
        }
        MovieDetailsUiState.ErrorUiState -> {
            ErrorMessage("Error. Something went wrong :(")
        }
        MovieDetailsUiState.LoadingUiState -> {
            ProgressCircle()
        }
    }
}

@Composable
fun MovieDetails(
    movieDetailsUiItem: MovieDetailsUiModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = movieDetailsUiItem.backdropPath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movieDetailsUiItem.originalTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 26.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movieDetailsUiItem.posterPath,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth(0.4f)
                    .aspectRatio(2f / 3f)
                    .clip(shape = RoundedCornerShape(4.dp)),
            )
            Text(
                modifier = Modifier.padding(end = 12.dp, top = 12.dp, bottom = 12.dp),
                text = movieDetailsUiItem.overview,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Release Date: ",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = movieDetailsUiItem.releaseDate,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Duration: ",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = movieDetailsUiItem.runtime.toString() + "m",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Vote Average: ",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = movieDetailsUiItem.voteAverage.toString() + "/10",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Vote Reviews: ",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = movieDetailsUiItem.voteCount.toString()
            )
        }
    }
}