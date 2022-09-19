package com.example.newsapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    var BASEURL = "https://newsapi.org/v2/"

    fun getRetrofoit(): Retrofit {

        var retrofit = Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit

    }

}

