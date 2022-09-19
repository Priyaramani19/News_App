package com.example.newsapp.Fragment.Screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter.GNewsINAdapter
import com.example.newsapp.ArticlesItem
import com.example.newsapp.GNewsINModel
import com.example.newsapp.Retrofit.APIClient
import com.example.newsapp.Retrofit.APIInterface
import com.example.newsapp.databinding.FragmentTopNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopNewsFragment : Fragment() {

    lateinit var tnBinding: FragmentTopNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tnBinding = FragmentTopNewsBinding.inflate(inflater, container, false)

        getGNews()

        return tnBinding.root
    }

    fun getGNews() {

        var apiInterface = APIClient().getRetrofoit().create(APIInterface::class.java)
        apiInterface.getGNewsIN("google-news", "532a522ab79744f7a07aec970a5bdeaf")
            .enqueue(object : Callback<GNewsINModel> {
                override fun onResponse(call: Call<GNewsINModel>, response: Response<GNewsINModel>) {
                    var list = response.body()?.articles!!

                    tnBinding.shimmerEffect.isVisible = false

                    setUpRV(list)

                    Log.e("TAG", "onResponse: top News ======= $list")
                }

                override fun onFailure(call: Call<GNewsINModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })
    }

    fun setUpRV(l1: List<ArticlesItem?>) {

        var adapter = GNewsINAdapter(activity, l1)
        var layoutManager = LinearLayoutManager(activity)
        tnBinding.rvView.adapter = adapter
        tnBinding.rvView.layoutManager = layoutManager
    }

}