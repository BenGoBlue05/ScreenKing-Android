package com.example.screenking.api

import com.example.screenking.vo.MovieDetails
import com.example.screenking.vo.MovieSummary
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepo {
    fun loadMovies(): Flowable<List<MovieSummary>>

    fun loadMovieDetails(movieId: Int): Flowable<MovieDetails>
}

@Singleton
class DefaultMovieRepo @Inject constructor(
    private val tmdbService: TMDBService
) : MovieRepo {

    override fun loadMovies(): Flowable<List<MovieSummary>> {
        return tmdbService.getMovies()
            .map { it.results.map(MovieSummary.Companion::create) }
            .toFlowable()
    }

    override fun loadMovieDetails(movieId: Int): Flowable<MovieDetails> {
        return tmdbService.getMovieDetails(movieId)
            .map(MovieDetails.Companion::create)
            .toFlowable()
    }
}