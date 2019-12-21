package com.example.screenking.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.screenking.R

@BindingAdapter("posterPath")
fun loadPosterPath(imageView: ImageView, posterPath: String?) {
    if (posterPath.isNullOrBlank()) return
    Glide.with(imageView)
        .load("https://image.tmdb.org/t/p/w200/$posterPath")
        .placeholder(R.drawable.ic_local_movies)
        .into(imageView)
}