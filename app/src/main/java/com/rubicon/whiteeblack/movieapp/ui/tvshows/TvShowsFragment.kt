package com.rubicon.whiteeblack.movieapp.ui.tvshows

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.adapter.TvShowAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils


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
        setHasOptionsMenu(true)
        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TvShowAdapter()
        recyclerView.adapter = adapter
        val viewModelFactory = InjectorUtils.provideTvShowViewModelFactory()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TvShowViewModel::class.java)
        viewModel.topRatedTvShows().observe(this, Observer { result ->
            if (result != null)
                adapter.submitList(result.tvShows)
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main2,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.menu_item_search ->
            {
                findNavController().navigate(R.id.searchTvShowFragment)
                return true
            }
        }
        return false
    }
}
