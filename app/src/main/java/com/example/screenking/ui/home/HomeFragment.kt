package com.example.screenking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.screenking.R
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.home_fragment.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private val disposables: MutableList<Disposable> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MovieAdapter(viewLifecycleOwner, viewModel)
        with(moviesRV) {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        disposables += viewModel.movies.subscribe(adapter::submitList, Timber::e)
        disposables += viewModel.viewMovieDetails.subscribe {
            Timber.d("Movie Summary: $it")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.forEach(Disposable::dispose)
        disposables.clear()
    }
}