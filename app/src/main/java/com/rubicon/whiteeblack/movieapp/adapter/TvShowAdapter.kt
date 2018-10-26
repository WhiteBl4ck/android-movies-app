package com.rubicon.whiteeblack.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rubicon.whiteeblack.movieapp.HomeFragmentDirections
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.model.TvShow

import com.rubicon.whiteeblack.movieapp.utils.buildImageUrlFromPosterPath

class TvShowAdapter : ListAdapter<TvShow, TvShowAdapter.TvShowHolder>(TvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return TvShowHolder(view)
    }

    override fun onBindViewHolder(tvShowHolder: TvShowHolder, position: Int) {
        val tvShow = getItem(position)
        tvShowHolder.bind(tvShow)
    }
    class TvShowHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var textView : TextView = itemView.findViewById(R.id.textView)
        var imageView : ImageView = itemView.findViewById(R.id.imageView)

        private fun createOnClickListener(title :String,imageUrl : String, description : String): View.OnClickListener {
            return View.OnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(title,imageUrl,description)
                it.findNavController().navigate(direction)
            }
        }

        fun bind(item : TvShow)
        {
            val title = item.name
            val imageUrl = buildImageUrlFromPosterPath(item.posterPath)
            val description = item.overview

            // update UI
            textView.text = item.name
            Glide.with(imageView.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            // create on click listener with TvShow Detail Information
            itemView.setOnClickListener(createOnClickListener(title,imageUrl,description))
        }
    }
}