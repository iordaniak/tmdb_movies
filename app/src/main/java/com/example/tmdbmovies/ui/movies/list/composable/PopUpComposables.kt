package com.example.tmdbmovies.ui.movies.list.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.tmdbmovies.ui.movies.list.model.MovieUiModel
import com.example.tmdbmovies.ui.theme.YellowStar


@Composable
fun PopupScreen(
    onClickOutside: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray.copy(alpha = 0.75f))
            .zIndex(10F)
            .clickable { onClickOutside() },
        contentAlignment = Alignment.Center
    ) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(excludeFromSystemGesture = true),
            onDismissRequest = { onClickOutside() }
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

@Composable
fun PopupMovieDetails(movie: MovieUiModel){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row{
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(2f / 3f)
            )
            Column(
                modifier =  Modifier.padding(horizontal = 10.dp)
            ){
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = movie.releaseDate)
                Row {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = YellowStar,
                    )
                    Text(
                        text = movie.voteAverage.toString(),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 6.dp)
                    )
                    Text(text = "(${movie.voteCount})")
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Details")
            }
        }
    }
}

@Preview
@Composable
fun PopupMovieDetailsPreview(){
    PopupMovieDetails(
        movie = MovieUiModel(
            id = 1,
            language = "EN",
            title = "12 Angry Men",
            overview = "The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.",
            posterPath = "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/ppd84D2i9W8jXmsyInGyihiSyqz.jpg",
            releaseDate = "SSS",
            voteAverage = 7.62,
            voteCount = 60,
            isFavorite = false
        )
    )
}