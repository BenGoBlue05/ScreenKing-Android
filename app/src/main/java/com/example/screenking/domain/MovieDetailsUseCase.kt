package com.example.screenking.domain

import com.example.screenking.api.MovieRepo
import com.example.screenking.vo.MovieDetails
import io.reactivex.Observable
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val movieRepo: MovieRepo
) {
    operator fun invoke(movieId: Int): Observable<MovieDetails> =
        movieRepo.loadMovieDetails(movieId)
}