package com.example.screenking.domain

import com.example.screenking.api.MovieRepo
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val movieRepo: MovieRepo
) {
    operator fun invoke() = movieRepo.getMovies()
}