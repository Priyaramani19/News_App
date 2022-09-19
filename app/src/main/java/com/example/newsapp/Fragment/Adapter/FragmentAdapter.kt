package com.example.newsapp.Fragment.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.newsapp.Fragment.Screen.*
import com.example.newsapp.FragmentActivity.MainFragment

class FragmentAdapter(activity: FragmentActivity?, childFragmentManager: FragmentManager, ) :
    FragmentPagerAdapter(childFragmentManager) {
    override fun getCount(): Int {
        return 8
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopNewsFragment()
            1 -> IndiaFragment()
            2 -> BusinessFragment()
            3 -> SportFragment()
            4 -> PoliticsFragment()
            5 -> TechnologyFragment()
            6 -> ScienceFragment()
            7 -> EntertainmentFragment()
            else -> HealthFragment()
        }
    }

}
