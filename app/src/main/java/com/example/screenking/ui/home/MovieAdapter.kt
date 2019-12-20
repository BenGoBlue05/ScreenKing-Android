package com.example.screenking.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.screenking.databinding.MovieItemBinding
import com.example.screenking.vo.MovieSummary

class MovieAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val listener: MovieSelectedListener
) : ListAdapter<MovieSummary, MovieAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<MovieSummary>() {
        override fun areItemsTheSame(oldItem: MovieSummary, newItem: MovieSummary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieSummary, newItem: MovieSummary): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            lifecycleOwner = this@MovieAdapter.lifecycleOwner
            movie = getItem(position)
            listener = this@MovieAdapter.listener
        }
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)
}