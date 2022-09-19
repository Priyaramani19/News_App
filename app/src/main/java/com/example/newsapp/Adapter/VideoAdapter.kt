package com.example.newsapp.Adapter

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.core.view.MotionEventCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.Screen.VideoActivity
import com.example.newsapp.VideoModelClass.ModelClass

class VideoAdapter(val videoActivity: VideoActivity, val videoItems: ArrayList<ModelClass>) :
    RecyclerView.Adapter<VideoAdapter.ViewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(videoActivity).inflate(R.layout.videoview_item, parent, false)

        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.setVideo(videoActivity, videoItems[position])
    }

    override fun getItemCount(): Int {
        return videoItems.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        var txt = itemView.findViewById<TextView>(R.id.txt)
        var videoView = itemView.findViewById<VideoView>(R.id.videoView)
        var b = 0
        lateinit var mp : MediaPlayer

        @SuppressLint("ClickableViewAccessibility")
        fun setVideo(videoActivity: VideoActivity, videoItem: ModelClass) {

            videoView.setOnTouchListener { v, event ->
                return@setOnTouchListener when (MotionEventCompat.getActionMasked
                    (event)) {
                    MotionEvent.ACTION_DOWN -> {
                        videoView.pause()
                        true
                    }
                    else -> {
                        videoView.start()
                        false
                    }
                }
            }

            videoView.setOnClickListener {
                if(videoView.isPlaying){
                    if (b == 0) {
                        mp.setVolume(0f, 0f)
                        txt.text = "Mute"
                        b=1;
                    } else {
                        mp.setVolume(1F, 1F)
                        txt.text = "Unmute"
                        b=0;
                    }
                }
            }

            videoView.setVideoPath(videoItem.videoUrl)

            videoView.setOnPreparedListener { mp ->

                progressBar.isVisible = false
                mp.start()
                val videoRatio = mp.videoWidth / mp.videoHeight.toFloat()
                val screenRatio = videoView.width / videoView.height.toFloat()
                val scale = videoRatio / screenRatio
                if (scale >= 1f) {
                    videoView.scaleX = scale
                } else {
                    videoView.scaleY = 1f / scale
                }
            }
            videoView.setOnCompletionListener { mp -> mp.start() }

        }


    }

}