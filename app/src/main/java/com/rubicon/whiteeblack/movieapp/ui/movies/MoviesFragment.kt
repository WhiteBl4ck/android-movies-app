package com.rubicon.whiteeblack.movieapp.ui.movies

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.adapter.MovieAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils


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
        setHasOptionsMenu(true)

        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MovieAdapter()
        recyclerView.adapter = adapter
        val viewModelFactory = InjectorUtils.provideMoviesViewModelFactory()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MoviesViewModel::class.java)
        viewModel.topRatedMoviesResponse().observe(this, Observer { result ->
            if (result != null)
                adapter.submitList(result.movies)
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main2,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.menu_item_search -> {
                findNavController().navigate(R.id.searchMoviesFragment)
                return true
            }

        }
        return false
    }

}
