package com.example.tmdbmovies.di

import android.content.Context
import androidx.room.Room
import com.example.tmdbmovies.framework.database.MovieDao
import com.example.tmdbmovies.framework.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase{
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database").build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao{
        return movieDatabase.movieDao()
    }
}
