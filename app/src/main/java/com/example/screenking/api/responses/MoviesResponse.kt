package com.example.screenking.api.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    @SerializedName("total_results") val numResults: Int,
    @SerializedName("total_pages") val numPages: Int,
    val results: List<Model>
) {
    data class Model(
        val title: String,
        @SerializedName("release_date") val releaseDate: String,
        val overview: String,
        @SerializedName("genre_ids") val genreIds: List<Int>,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("backdrop_path") val backdropPath: String?
    )
}