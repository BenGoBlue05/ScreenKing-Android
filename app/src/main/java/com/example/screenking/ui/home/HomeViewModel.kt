package com.example.screenking.ui.home

import androidx.lifecycle.ViewModel
import com.example.screenking.domain.MoviesUseCase
import com.example.screenking.vo.MovieSummary
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    moviesUseCase: MoviesUseCase
) : ViewModel(), MovieSelectedListener {

    val movies: Flowable<List<MovieSummary>> =
        moviesUseCase().observeOn(AndroidSchedulers.mainThread())

    override fun onMovieSelected(movie: MovieSummary) {
        Timber.d("Selected movie: $movie")
    }

}

interface MovieSelectedListener {
    fun onMovieSelected(movie: MovieSummary)
}