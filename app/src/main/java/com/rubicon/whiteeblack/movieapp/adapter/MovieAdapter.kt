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
import com.rubicon.whiteeblack.movieapp.model.Movie
import com.rubicon.whiteeblack.movieapp.utils.buildImageUrlFromPosterPath

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(movieHolder: MovieHolder, position: Int) {
        val movie = getItem(position)
        movieHolder.bind(movie)
    }


    class MovieHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var textView : TextView = itemView.findViewById(R.id.textView)
        var imageView : ImageView = itemView.findViewById(R.id.imageView)

        // open detailfragment on click
        private fun createOnClickListener(title :String,imageUrl : String, description : String): View.OnClickListener {
            return View.OnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(title,imageUrl,description)
                it.findNavController().navigate(direction)
            }
        }

        fun bind(item : Movie)
        {
            val title = item.title
            val imageUrl = buildImageUrlFromPosterPath(item.posterPath)
            val description = item.overview

            // update UI
            textView.text = item.title
            Glide.with(imageView.context)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            // create on click listener with Movie Detail Information
            itemView.setOnClickListener(createOnClickListener(title,imageUrl,description))
        }
    }
}