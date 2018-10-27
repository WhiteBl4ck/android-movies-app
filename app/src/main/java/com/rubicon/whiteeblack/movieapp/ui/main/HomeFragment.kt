package com.rubicon.whiteeblack.movieapp.ui.main


import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import android.view.*
import com.rubicon.whiteeblack.movieapp.R
import com.rubicon.whiteeblack.movieapp.adapter.SectionPagerAdapter
import com.rubicon.whiteeblack.movieapp.ui.movies.MoviesFragment
import com.rubicon.whiteeblack.movieapp.ui.tvshows.TvShowsFragment

class HomeFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabs)
        val viewPager = view.findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewpager)
        val adapter = SectionPagerAdapter(childFragmentManager)

        //add fragments to viewpager
        adapter.addFragment(MoviesFragment.newInstance(),"movies")
        adapter.addFragment(TvShowsFragment.newInstance(),"tvshows")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        return view
    }
}
