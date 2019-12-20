package com.example.screenking.ui.home

import androidx.lifecycle.ViewModel
import com.example.screenking.api.MovieRepo
import com.example.screenking.vo.MovieSummary
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    repo: MovieRepo
) : ViewModel() {

    val movies: Flowable<List<MovieSummary>> = repo.getMovies()
        .observeOn(AndroidSchedulers.mainThread())

}