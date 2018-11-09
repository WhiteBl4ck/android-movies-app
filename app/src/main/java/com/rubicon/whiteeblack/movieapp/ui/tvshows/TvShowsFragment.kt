package com.rubicon.whiteeblack.movieapp.ui.tvshows

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.adapter.TvShowAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils
import com.rubicon.whiteeblack.movieapp.vo.Status


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
        val errorLayout = view.findViewById<LinearLayout>(R.id.layout_error)
        val errorText = view.findViewById<TextView>(R.id.text_error)
        val buttonRetry = view.findViewById<Button>(R.id.button_retry)
        buttonRetry.setOnClickListener{
            viewModel.loadTopRatedTvShows()
        }
        val progressionBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TvShowAdapter()
        recyclerView.adapter = adapter
        val viewModelFactory = InjectorUtils.provideTvShowViewModelFactory()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TvShowViewModel::class.java)
        viewModel.topRatedTvShows().observe(this, Observer { result ->
            when(result.status)
            {
                Status.LOADING ->
                {
                    errorLayout.visibility = View.GONE
                    progressionBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressionBar.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    errorText.text = StringBuffer().append(resources.getString(R.string.error_loading_page)).append("\n").append(result.message)
                }
                Status.SUCCESS ->
                {
                    errorLayout.visibility = View.GONE
                    progressionBar.visibility = View.GONE
                    if (result.data != null)
                        adapter.submitList(result.data.tvShows.subList(0,10))
                }
            }
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
