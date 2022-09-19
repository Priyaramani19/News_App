package com.example.newsapp.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.Adapter.VideoAdapter
import com.example.newsapp.VideoModelClass.ModelClass
import com.example.newsapp.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    var videoItems = ArrayList<ModelClass>()
    lateinit var videoBinding : ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoBinding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(videoBinding.root)


        videoItems.add(ModelClass("https://www.flexclip.com/res/preview/226p/live-streaming-trailer.mp4"))
        videoItems.add(ModelClass("https://assets.mixkit.co/videos/preview/mixkit-the-spheres-of-a-christmas-tree-2720-large.mp4"))
        videoItems.add(ModelClass("https://assets.mixkit.co/videos/preview/mixkit-boardwalk-with-umbrellas-1165-large.mp4"))

        var videoAdapter = VideoAdapter(this,videoItems)
        videoBinding.viewPager.adapter = videoAdapter


    }

}