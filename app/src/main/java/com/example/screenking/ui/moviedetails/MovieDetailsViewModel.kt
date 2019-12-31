package com.example.screenking.ui.moviedetails

import androidx.lifecycle.ViewModel
import com.example.screenking.domain.MovieDetailsUseCase
import com.example.screenking.ui.RetryListener
import com.example.screenking.vo.MovieDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase
) : ViewModel(), RetryListener {

    val showLoadingIndicator: BehaviorSubject<Boolean> = BehaviorSubject.create()

    val retry: PublishSubject<Unit> = PublishSubject.create()

    val displayRetryButton: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)


    val movieDetails: BehaviorSubject<MovieDetails> = BehaviorSubject.create()

    fun setMovieId(id: Int) {
        showLoadingIndicator.onNext(true)
        displayRetryButton.onNext(false)

        movieDetailsUseCase(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { showLoadingIndicator.onNext(false) }
            .doOnError {
                showLoadingIndicator.onNext(false)
                displayRetryButton.onNext(true)
            }
            .subscribe(movieDetails)
    }

    override fun retry() {
        retry.onNext(Unit)
    }
}