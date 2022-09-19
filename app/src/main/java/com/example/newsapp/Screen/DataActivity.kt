package com.example.newsapp.Screen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDataBinding


class DataActivity : AppCompatActivity() {

    var url: String? = null
    var description: String? = null
    var publishedAt: String? = null
    var urlToImage: String? = null
    var title: String? = null
    var sourceName: String? = null
    lateinit var dataBinding: ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        sourceName = intent.getStringExtra("sourceName")
        title = intent.getStringExtra("title")
        urlToImage = intent.getStringExtra("urlToImage")
        publishedAt = intent.getStringExtra("publishedAt")
        description = intent.getStringExtra("description")
        url = intent.getStringExtra("url")

        initClick()
        urlUnderline()
        setData()


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    private fun initClick() {
        dataBinding.backBtn.setOnClickListener {
            onBackPressed()
        }

        dataBinding.readMoreTxt.setOnClickListener {
            var intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }
    }


    private fun setData() {

        dataBinding.sourceName.text = sourceName
        dataBinding.titleTxt.text = title
        dataBinding.publishTxt.text = publishedAt
        dataBinding.descriptionTxt.text = description

        Glide.with(this).load(urlToImage).placeholder(R.drawable.media).into(dataBinding.urlImage)

    }


    private fun urlUnderline() {
        val content = SpannableString("Read more >>")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        dataBinding.readMoreTxt.setText(content)
        dataBinding.readMoreTxt.setTextColor(Color.rgb(0, 0, 225))
    }

}