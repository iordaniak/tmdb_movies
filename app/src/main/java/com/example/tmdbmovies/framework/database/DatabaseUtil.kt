package com.example.tmdbmovies.framework.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
object DatabaseUtil {

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao { // Assuming you have an AppDatabase class
        return database.movieDao()
    }
    */
/*private lateinit var database: MovieDatabase

    fun getDatabase(context: Context): MovieDatabase {
        if (!::database.isInitialized) {
            synchronized(this) {
                if (!::database.isInitialized) {
                    database = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie_database")
                        .build()
                }
            }
        }
        return database
    }*//*

}*/

@Module
@InstallIn(SingletonComponent::class)
object DatabaseUtil {

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao { // Replace AppDatabase with your actual database class
        return database.movieDao()
    }
}

@Provides
@Singleton
fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDatabase {
    return Room.databaseBuilder(
        appContext,
        MovieDatabase::class.java,
        "movie_database"
    ).build()
}