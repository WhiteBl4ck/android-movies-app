package com.rubicon.whiteeblack.movieapp.adapter.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.rubicon.whiteeblack.movieapp.model.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}