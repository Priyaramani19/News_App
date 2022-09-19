package com.example.newsapp.FragmentActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.Fragment.Adapter.FragmentAdapter
import com.example.newsapp.Screen.SearchActivity
import com.example.newsapp.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayout

class NewsFragment : Fragment() {

    lateinit var newsFragmentBinding : FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsFragmentBinding = FragmentNewsBinding.inflate(inflater,container,false)

        setFragmentToTab()
        initClick()

        return newsFragmentBinding.root
    }

    private fun initClick() {
        newsFragmentBinding.searchImg.setOnClickListener {
            var intent = Intent(activity,SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setFragmentToTab() {

        newsFragmentBinding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Top\nNews"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("India"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Business"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Sport"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Politics"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Technology"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Science"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Entertainment"))
        newsFragmentBinding.tabLayout.addTab(newsFragmentBinding.tabLayout.newTab().setText("Health"))


        var adapter = FragmentAdapter(activity,childFragmentManager)
        newsFragmentBinding.viewPager.adapter = adapter

        newsFragmentBinding.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                newsFragmentBinding.tabLayout
            )
        )

        newsFragmentBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                newsFragmentBinding.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}