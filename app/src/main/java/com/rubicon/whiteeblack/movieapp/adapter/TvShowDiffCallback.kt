package com.rubicon.whiteeblack.movieapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rubicon.whiteeblack.movieapp.model.TvShow

class TvShowDiffCallback : DiffUtil.ItemCallback<TvShow>(){
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem == newItem
    }

}
