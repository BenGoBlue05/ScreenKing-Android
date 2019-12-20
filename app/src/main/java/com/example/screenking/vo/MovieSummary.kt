package com.example.screenking.vo

import com.example.screenking.api.responses.MoviesResponse

data class MovieSummary(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val genreIds: List<Int>,
    val posterPath: String?,
    val backdropPath: String?
) {
    companion object {
        fun create(responseModel: MoviesResponse.Model) =
            responseModel.run {
                MovieSummary(
                    id = id,
                    title = title,
                    releaseDate = releaseDate,
                    overview = overview,
                    genreIds = genreIds,
                    posterPath = posterPath,
                    backdropPath = backdropPath
                )
            }
    }
}