package com.rubicon.whiteeblack.movieapp.adapter


class SectionPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private var mFragmentItems : ArrayList<androidx.fragment.app.Fragment> = ArrayList()
    private var mFragmenTitles : ArrayList<String> = ArrayList()

    fun addFragment(fragment: androidx.fragment.app.Fragment, title : String)
    {
        mFragmentItems.add(fragment)
        mFragmenTitles.add(title)
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return mFragmentItems[position]
    }

    override fun getCount(): Int {
        return mFragmentItems.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmenTitles[position]
    }
}