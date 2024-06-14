package com.example.tmdbmovies.ui.movies.list.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector


data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)


val listOfNavItems = listOf(
    NavItem(
        label = "Popular",
        icon = Icons.AutoMirrored.Filled.List,
        route = Screens.PopularMoviesScreen.name
    ),
    NavItem(
        label = "Favorite",
        icon = Icons.Filled.Favorite,
        route = Screens.FavoriteMoviesScreen.name
    )
)