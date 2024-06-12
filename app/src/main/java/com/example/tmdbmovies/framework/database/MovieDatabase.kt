package com.example.tmdbmovies.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdbmovies.domain.list.entity.MovieModel

@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                    .build()
                    .also { Instance = it}
            }
        }
    }
}