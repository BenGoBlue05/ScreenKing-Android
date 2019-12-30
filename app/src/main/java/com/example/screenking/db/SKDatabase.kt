package com.example.screenking.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.screenking.vo.MovieDetails

@Database(
    entities = [
        MovieDetails::class
    ], version = 1
)
abstract class SKDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}