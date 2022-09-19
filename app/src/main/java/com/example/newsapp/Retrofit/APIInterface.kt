package com.example.newsapp.Retrofit

import com.example.newsapp.GNewsINModel
import com.example.newsapp.ModelClassAPI.CountryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("top-headlines")
    fun getGNewsIN(
        @Query("sources") source: String,
        @Query("apiKey") key: String
    ): Call<GNewsINModel>

    @GET("top-headlines")
    fun getCountry(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") key: String
    ) : Call<CountryModel>

}


//https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=532a522ab79744f7a07aec970a5bdeaf
//https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=9e7f0f8acb424c2bab1fa40143ab037b