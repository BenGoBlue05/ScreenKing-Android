package com.example.screenking.vo

import com.example.screenking.api.responses.MovieDetailsResponse

data class MovieDetails(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String?,
    val backdropPath: String?,
    val tagline: String?,
    val voteAverage: Double,
    val voteCount: Int,
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
                    runtime = runtime
                )
            }
    }
}