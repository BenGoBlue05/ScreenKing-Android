package com.example.screenking.ui.home

import androidx.lifecycle.ViewModel
import com.example.screenking.domain.MoviesUseCase
import com.example.screenking.vo.MovieSummary
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    moviesUseCase: MoviesUseCase
) : ViewModel(), MovieSelectedListener {

    val viewMovieDetails: PublishSubject<MovieSummary> = PublishSubject.create()

    val showLoadingIndicator: BehaviorSubject<Boolean> = BehaviorSubject.create()

    val movies: Flowable<List<MovieSummary>> = moviesUseCase()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext { showLoadingIndicator.onNext(false) }
        .doOnError { showLoadingIndicator.onNext(false) }

    init {
        showLoadingIndicator.onNext(true)
    }

    override fun onMovieSelected(movie: MovieSummary) {
        viewMovieDetails.onNext(movie)
    }

}

interface MovieSelectedListener {
    fun onMovieSelected(movie: MovieSummary)
}