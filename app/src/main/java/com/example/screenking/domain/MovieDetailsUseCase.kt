package com.example.screenking.domain

import com.example.screenking.api.MovieRepo
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val movieRepo: MovieRepo
) {
    operator fun invoke(movieId: Int) =
        movieRepo.loadMovieDetails(movieId)
}