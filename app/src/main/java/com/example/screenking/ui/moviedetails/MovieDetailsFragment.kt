package com.example.screenking.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.screenking.R
import com.example.screenking.vo.MovieDetails
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.movie_details_fragment.*
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
        disposableContainer.addAll(
            viewModel.movieDetails.subscribe(this::updateUI, Timber::e),
            viewModel.showLoadingIndicator.subscribe { loadingIndicator.isVisible = it }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposableContainer.clear()
    }

    private fun updateUI(movie: MovieDetails) {
        with(movie) {
            titleTV.text = title
            taglineTv.text = tagline
            overviewTV.text = overview
            Glide.with(headerIV)
                .load("https://image.tmdb.org/t/p/w500/$backdropPath")
                .into(headerIV)
        }

    }
}