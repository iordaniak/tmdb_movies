package com.example.tmdbmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tmdbmovies.ui.TmdbMoviesApp
import com.example.tmdbmovies.ui.theme.TmdbMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmdbMoviesTheme {
                TmdbMoviesApp()
            }
        }
    }
}
