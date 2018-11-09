package com.rubicon.whiteeblack.movieapp.ui.movies

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.adapter.MovieAdapter
import com.rubicon.whiteeblack.movieapp.utils.InjectorUtils
import com.rubicon.whiteeblack.movieapp.vo.Status


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
        val errorLayout = view.findViewById<LinearLayout>(R.id.layout_error)
        val errorText = view.findViewById<TextView>(R.id.text_error)
        val buttonRetry = view.findViewById<Button>(R.id.button_retry)
        buttonRetry.setOnClickListener{
            viewModel.loadTopRatedMovies()
        }
        val progressionBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MovieAdapter()
        recyclerView.adapter = adapter
        val viewModelFactory = InjectorUtils.provideMoviesViewModelFactory()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MoviesViewModel::class.java)
        viewModel.topRatedMoviesResponse().observe(this, Observer { result ->
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
                        adapter.submitList(result.data.movies.subList(0,10))
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
            R.id.menu_item_search -> {
                findNavController().navigate(R.id.searchMoviesFragment)
                return true
            }

        }
        return false
    }

}
