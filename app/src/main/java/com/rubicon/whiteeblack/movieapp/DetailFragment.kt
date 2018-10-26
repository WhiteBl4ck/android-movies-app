package com.rubicon.whiteeblack.movieapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils
import com.rubicon.whiteeblack.movieapp.viewmodels.DetailViewModel
import kotlinx.android.synthetic.main.list_item.*


class DetailFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageViewPoster : ImageView = view.findViewById(R.id.imageViewPoster)
        val textViewTitle : TextView = view.findViewById(R.id.textViewTitle)
        val textViewDescription : TextView = view.findViewById(R.id.editTextDescription)
        val title : String = DetailFragmentArgs.fromBundle(arguments).title
        val imageUrl : String = DetailFragmentArgs.fromBundle(arguments).imageUrl
        val description : String = DetailFragmentArgs.fromBundle(arguments).description

        val factory = InjectorUtils.provideDetailViewModelFactory(title,imageUrl,description)
        viewModel = ViewModelProviders.of(this,factory).get(DetailViewModel::class.java)
        viewModel.getMovie().observe(this, Observer {
            movie ->
            textViewTitle.text = movie.title
            textViewDescription.text = movie.description
            Glide.with(context!!)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewPoster)
        })
    }


}
