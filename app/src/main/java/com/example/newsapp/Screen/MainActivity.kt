package com.example.newsapp.Screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsapp.FragmentActivity.MainFragment
import com.example.newsapp.FragmentActivity.NewsFragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        loadFragment(MainFragment())

    }

    override fun onResume() {
        super.onResume()
        setNavigation()
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.viewPager, fragment)
            commit()
        }
    }

    fun setNavigation() {

        mainBinding.navigationBar.setOnItemSelectedListener {
            when (it) {
                0 -> {
                    loadFragment(MainFragment())
                }
                1 -> {
                    loadFragment(NewsFragment())
                }
                2 -> {
                    var intent = Intent(this,VideoActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}
