package com.rubicon.whiteeblack.movieapp

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rubicon.whiteeblack.movieapp.adapter.MovieAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils
import com.rubicon.whiteeblack.movieapp.viewmodels.MoviesViewModel


class MoviesFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)
        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MovieAdapter()
        recyclerView.adapter = adapter
        val viewModelFactory = InjectorUtils.provideMoviesViewModelFactory()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MoviesViewModel::class.java)
        viewModel.topRatedMoviesResponse.observe(this, Observer { result ->
            if (result != null)
                adapter.submitList(result.movies)
        })
        return view
    }

}
