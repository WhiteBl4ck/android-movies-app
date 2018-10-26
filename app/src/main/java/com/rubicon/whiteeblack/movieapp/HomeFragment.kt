package com.rubicon.whiteeblack.movieapp


import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.*
import android.widget.TableLayout

import kotlinx.android.synthetic.main.fragment_home.*

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
        adapter.addFragment(MoviesFragment.newInstance(),"movies")
        adapter.addFragment(TvShowsFragment.newInstance(),"tvshow11")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main2,menu)
    }
}
