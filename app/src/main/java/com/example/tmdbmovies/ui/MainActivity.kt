package com.example.tmdbmovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdbmovies.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
