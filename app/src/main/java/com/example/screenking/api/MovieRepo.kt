package com.example.screenking.api

import com.example.screenking.db.MovieDao
import com.example.screenking.vo.MovieDetails
import com.example.screenking.vo.MovieSummary
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepo {
    fun loadMovies(): Flowable<List<MovieSummary>>

    fun loadMovieDetails(movieId: Int): Observable<MovieDetails>
}

@Singleton
class DefaultMovieRepo @Inject constructor(
    private val tmdbService: TMDBService,
    private val movieDao: MovieDao
) : MovieRepo {

    override fun loadMovies(): Flowable<List<MovieSummary>> {
        return tmdbService.getMovies()
            .map { it.results.map(MovieSummary.Companion::create) }
            .toFlowable()
    }

    override fun loadMovieDetails(movieId: Int): Observable<MovieDetails> {
        return tmdbService.getMovieDetails(movieId)
            .flatMapCompletable { movieDao.insertMovieDetails(MovieDetails.create(it)) }
            .andThen(movieDao.loadMovieDetails(movieId))
    }
}