package com.example.tmdbmovies.framework.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbmovies.domain.list.entity.MovieModel

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieModel)

    @Query("SELECT * FROM movies")
    fun getAll(): LiveData<List<MovieModel>>

    @Delete
    suspend fun delete(movie: MovieModel)
}