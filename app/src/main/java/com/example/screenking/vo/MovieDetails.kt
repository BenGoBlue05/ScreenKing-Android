package com.example.screenking.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.screenking.api.responses.MovieDetailsResponse

@Entity
data class MovieDetails(
    @PrimaryKey val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String?,
    val backdropPath: String?,
    val tagline: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String?,
    val runtime: Int
) {
    companion object {
        fun create(response: MovieDetailsResponse) =
            response.run {
                MovieDetails(
                    id = id,
                    title = title,
                    releaseDate = releaseDate,
                    posterPath = posterPath,
                    backdropPath = backdropPath,
                    tagline = tagline,
                    voteAverage = voteAverage,
                    voteCount = voteCount,
                    overview = overview,
                    runtime = runtime
                )
            }
    }
}