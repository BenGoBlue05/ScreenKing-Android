package com.example.screenking.ui.moviedetails

import androidx.lifecycle.ViewModel
import com.example.screenking.domain.MovieDetailsUseCase
import com.example.screenking.vo.MovieDetails
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    movieDetailsUseCase: MovieDetailsUseCase
) : ViewModel() {

    private val movieId: BehaviorSubject<Int> = BehaviorSubject.create()

    val showLoadingIndicator: BehaviorSubject<Boolean> = BehaviorSubject.create()

    val movieDetails: Observable<MovieDetails> = movieId
        .flatMap(movieDetailsUseCase::invoke)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext { showLoadingIndicator.onNext(false) }
        .doOnError { showLoadingIndicator.onNext(false) }

    fun setMovieId(id: Int) {
        showLoadingIndicator.onNext(true)
        movieId.onNext(id)
    }
}