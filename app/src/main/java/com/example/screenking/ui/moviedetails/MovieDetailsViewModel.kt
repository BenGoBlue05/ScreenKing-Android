package com.example.screenking.ui.moviedetails

import androidx.lifecycle.ViewModel
import com.example.screenking.domain.MovieDetailsUseCase
import com.example.screenking.vo.MovieDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase
) : ViewModel() {

    val movieDetails: BehaviorSubject<MovieDetails> = BehaviorSubject.create()

    fun setMovieId(id: Int) {
        movieDetailsUseCase(id)
            .toObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(movieDetails)
    }
}