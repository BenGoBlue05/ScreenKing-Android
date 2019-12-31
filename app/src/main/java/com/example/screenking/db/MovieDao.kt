package com.example.screenking.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screenking.vo.MovieDetails
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(movieDetails: MovieDetails): Completable

    @Query("SELECT * FROM MovieDetails WHERE :id = id")
    fun loadMovieDetails(id: Int): Observable<MovieDetails>
}