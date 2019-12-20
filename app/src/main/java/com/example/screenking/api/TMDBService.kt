package com.example.screenking.api

import com.example.screenking.Secrets
import com.example.screenking.api.responses.MovieDetailsResponse
import com.example.screenking.api.responses.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey: String = Secrets.API_KEY_TMDB): Single<MoviesResponse>

    @GET("movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = Secrets.API_KEY_TMDB
    ): Single<MovieDetailsResponse>


}