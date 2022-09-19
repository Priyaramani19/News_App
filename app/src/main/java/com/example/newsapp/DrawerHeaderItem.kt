package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ActivityDrawerHeaderItemBinding

class DrawerHeaderItem : AppCompatActivity() {

    lateinit var drawerHeaderBinding : ActivityDrawerHeaderItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawerHeaderBinding = ActivityDrawerHeaderItemBinding.inflate(layoutInflater)
        setContentView(drawerHeaderBinding.root)

        Glide.with(this).load("https://www.turkishairlinesflightacademy.com/uploads/3611425.png").into(drawerHeaderBinding.circleImage)

    }
}