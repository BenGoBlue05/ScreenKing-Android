package com.example.screenking.api

import com.example.screenking.vo.MovieSummary
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepo {
    fun getMovies(): Flowable<List<MovieSummary>>
}

@Singleton
class DefaultMovieRepo @Inject constructor(
    private val tmdbService: TMDBService
) : MovieRepo {

    override fun getMovies(): Flowable<List<MovieSummary>> {
        return tmdbService.getMovies()
            .map { it.results.map(MovieSummary.Companion::create) }
            .toFlowable()
    }
}