package com.rubicon.whiteeblack.movieapp.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils

class DetailFragment : androidx.fragment.app.Fragment() {

    companion object {
        private const val IMAGE_URL : String = "com.rubicon.whiteeblack.movieapp.IMAGE_URL"
        private const val TITLE : String = "com.rubicon.whiteeblack.movieapp.TITLE"
        private const val DESCRIPTION : String = "com.rubicon.whiteeblack.movieapp.DESCRIPTION"

        fun setFragmentArguments(title : String,imageUrl : String,description : String) : Bundle
        {
            val bundle = Bundle()
            bundle.putString(TITLE,title)
            bundle.putString(IMAGE_URL,imageUrl)
            bundle.putString(DESCRIPTION,description)
            return bundle
        }
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Bind UI
        val imageViewPoster : ImageView = view.findViewById(R.id.imageViewPoster)
        val textViewTitle : TextView = view.findViewById(R.id.textViewTitle)
        val textViewDescription : TextView = view.findViewById(R.id.editTextDescription)

        // getArguments
        val title : String = arguments?.getString(TITLE) ?: ""
        val imageUrl : String = arguments?.getString(IMAGE_URL) ?: ""
        val description : String = arguments?.getString(DESCRIPTION) ?: ""

        // bind ViewModel
        val factory = InjectorUtils.provideDetailViewModelFactory(title,imageUrl,description)
        viewModel = ViewModelProviders.of(this,factory).get(DetailViewModel::class.java)

        // Observe viewModel
        viewModel.getMovie().observe(this, Observer {
            movie ->
            textViewTitle.text = movie.title
            textViewDescription.text = movie.description
            Glide.with(context!!)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().error(ColorDrawable(Color.RED)).placeholder(ColorDrawable(Color.RED)))
                .into(imageViewPoster)
        })
    }


}
