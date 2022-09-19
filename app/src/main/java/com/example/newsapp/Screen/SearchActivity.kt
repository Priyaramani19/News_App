package com.example.newsapp.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter.SearchAdapter
import com.example.newsapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    lateinit var searchBinding: ActivitySearchBinding

    var searchlist = arrayListOf(
        "BBC News",
        "CNN",
        "Times of India",
        "Bloomberg",
        "Fox News",
        "Reuters",
        "CBS News",
        "ABC News"
    )
    var sourceList = arrayListOf(
        "bbc-news",
        "cnn",
        "time",
        "bloomberg",
        "fox-news",
        "reuters",
        "cbs-news",
        "abc-news"
    )
    var channelImageList = arrayOf(
        "https://www.goldenvisas.com/images/BBC-News.jpg",
        "https://images.squarespace-cdn.com/content/v1/5d105bf05d7640000180e0be/1565010374235-VAR0V761CMVJP9VMXMM6/cnn-logo-white-on-red.jpg",
        "https://lh3.googleusercontent.com/utJ-k60Wxl9hng29MDPkuGxFqge7uRzKhdMtL75vnYAli4IMm72Sx3xUiLg44xUHlQi3rpqM=s0-p",
        "https://avatars.mds.yandex.net/i?id=ac3dbf2a60e5f24fd3cf5f32d6d341e9-5885384-images-thumbs&n=13&exp=1",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Fox_News_Channel_logo.svg/1200px-Fox_News_Channel_logo.svg.png",
        "https://www.hhri.org/wp-content/uploads/2018/02/Reuters.jpg",
        "https://i.pinimg.com/originals/96/e6/d9/96e6d9e141ac42bf9aad1aaae0a15c61.jpg",
        "https://avatars.mds.yandex.net/i?id=5d44ca1b33d2c8f886d7c491acbc12f0-5221319-images-thumbs&n=13&exp=1"
    )

    lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        setUpRV()
        search()
        initClick()

    }

    private fun initClick() {
        searchBinding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun setUpRV() {

        adapter = SearchAdapter(this@SearchActivity, searchlist, channelImageList,sourceList)
        var layoutManager = LinearLayoutManager(this)
        searchBinding.rvView.adapter = adapter
        searchBinding.rvView.layoutManager = layoutManager

    }


    fun search() {
        searchBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                filterSearch(newText!!)
                return false

            }
        })
    }

    fun filterSearch(text: String) {
        var filterSearchlist = arrayListOf<String>()

        for (x in searchlist) {
            if (x.lowercase().contains(text.lowercase())) {
                filterSearchlist.add(x)
            }
        }

        adapter.filterSearch(filterSearchlist)
    }
}