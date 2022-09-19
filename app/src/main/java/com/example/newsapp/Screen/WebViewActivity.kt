package com.example.newsapp.Screen

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var url: String
    lateinit var webViewBinding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(webViewBinding.root)

        url = intent.getStringExtra("url").toString()
        setWebView()
        initClick()

    }

    private fun initClick() {
        webViewBinding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setWebView() {

        webViewBinding.webView.loadUrl(url!!)
        webViewBinding.webView.getSettings().setJavaScriptEnabled(true)

        webViewBinding.webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })

        webViewBinding.webView.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webViewBinding.progressBar.setVisibility(View.VISIBLE)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webViewBinding.progressBar.setVisibility(View.GONE)
            }
        })
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (event.action == KeyEvent.ACTION_DOWN) {
//            when (keyCode) {
//                KeyEvent.KEYCODE_BACK -> {
//                    if (webViewBinding.webView.canGoBack()) {
//                        webViewBinding.webView.goBack()
//                    } else {
//                        showDialog()
//                    }
//                    return true
//                }
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//    fun showDialog() {
//        val alertDialog = AlertDialog.Builder(this@webViewActivity)
//            .setMessage("Are you sure you want to exit?")
//            .setPositiveButton("Yes") { dialogInterface, i ->
//                finish()
//            }
//            .setNegativeButton(
//                "No"
//            ) { dialogInterface, i ->
//            }
//            .create()
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//    }


}