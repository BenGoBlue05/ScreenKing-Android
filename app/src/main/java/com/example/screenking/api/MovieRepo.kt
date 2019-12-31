package com.example.screenking.api

import com.example.screenking.db.MovieDao
import com.example.screenking.vo.MovieDetails
import com.example.screenking.vo.MovieSummary
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepo {
    fun loadMovies(): Flowable<List<MovieSummary>>

    fun loadMovieDetails(movieId: Int): Observable<MovieDetails>
}

@Singleton
class DefaultMovieRepo @Inject constructor(
    private val tmdbService: TMDBService,
    private val movieDao: MovieDao,
    private val movieDetailsRateLimiter: RateLimiter<Int>
) : MovieRepo {

    override fun loadMovies(): Flowable<List<MovieSummary>> {
        return tmdbService.getMovies()
            .map { it.results.map(MovieSummary.Companion::create) }
            .toFlowable()
    }

    override fun loadMovieDetails(movieId: Int): Observable<MovieDetails> {
        val compositeDisposable = CompositeDisposable()
        val res: BehaviorSubject<MovieDetails> = BehaviorSubject.create()
        movieDao.loadMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .doOnTerminate { compositeDisposable.dispose() }
            .subscribe(res)

        if (movieDetailsRateLimiter.shouldFetch(movieId)) {
            compositeDisposable.add(
                tmdbService.getMovieDetails(movieId)
                    .flatMapCompletable { movieDao.insertMovieDetails(MovieDetails.create(it)) }
                    .subscribeBy(
                        onError = {
                            movieDetailsRateLimiter.reset(movieId)
                            if (!res.hasValue()) {
                                res.onError(it)
                            }
                        }
                    )
            )
        }
        return res
    }
}