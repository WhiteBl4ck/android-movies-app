package com.rubicon.whiteeblack.movieapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubicon.whiteeblack.movieapp.adapter.MovieAdapter
import com.rubicon.whiteeblack.movieapp.adapter.TvShowAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils
import com.rubicon.whiteeblack.movieapp.viewmodels.TvShowViewModel


class TvShowsFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tv_shows_fragment, container, false)
        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TvShowAdapter()
        recyclerView.adapter = adapter
        val viewModelFactory = InjectorUtils.provideTvShowViewModelFactory()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TvShowViewModel::class.java)
        viewModel.topRatedTvShows.observe(this, Observer { result ->

            if (result != null)
                adapter.submitList(result.tvShows)
        })
        return view
    }

}
