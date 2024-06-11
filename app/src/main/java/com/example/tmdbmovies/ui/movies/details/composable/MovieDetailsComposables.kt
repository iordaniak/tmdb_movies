package com.example.tmdbmovies.ui.movies.details.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tmdbmovies.ui.movies.details.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    movieId: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Details Screen $movieId"
        )
    }
}