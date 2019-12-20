package com.example.screenking.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.screenking.R
import com.example.screenking.vo.MovieDetails
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    private val disposableContainer = CompositeDisposable()

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setMovieId(args.movieId)
        disposableContainer.add(
            viewModel.movieDetails
                .subscribe(this::logSuccess, Timber::e)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposableContainer.clear()
    }

    private fun logSuccess(movie: MovieDetails) {
        Timber.d("Movie Details: $movie")
    }
}