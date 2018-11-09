package com.rubicon.whiteeblack.movieapp.ui.searchtvshows

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.adapter.TvShowAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils

class SearchTvShowsFragment : Fragment() {

    companion object {
    }

    private lateinit var viewModel: SearchTvShowsViewModel
    private lateinit var adapter : TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_tvshows_fragment, container, false)
        setHasOptionsMenu(true)
        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TvShowAdapter()
        recyclerView.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = InjectorUtils.provideSearchTvShowsViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(SearchTvShowsViewModel::class.java)
        viewModel.query.observe(this, Observer {
            viewModel.searchTvShows(it).observe(viewLifecycleOwner, Observer { result ->
                if (result != null)
                    adapter.submitList(result.tvShows)
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        // setup search view
        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchItem.expandActionView() // when fragment opens it expanded auto
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length >= 3)
                viewModel.query.value = newText

                return false
            }
        })
    }

}
