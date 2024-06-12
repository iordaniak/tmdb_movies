package com.example.tmdbmovies.framework.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.tmdbmovies.domain.list.entity.MovieModel
import javax.inject.Inject

class FavoritesRepository @Inject constructor (
    private val movieDao: MovieDao
) {

    val favorites: LiveData<List<MovieModel>> = movieDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: MovieModel) {
        movieDao.insert(movie)
    }

    suspend fun delete(movie: MovieModel){
        movieDao.delete(movie)
    }
}