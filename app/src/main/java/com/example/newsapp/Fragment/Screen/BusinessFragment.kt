package com.example.newsapp.Fragment.Screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter.CountryAdapter
import com.example.newsapp.ModelClassAPI.CountryModel
import com.example.newsapp.Retrofit.APIClient
import com.example.newsapp.Retrofit.APIInterface
import com.example.newsapp.databinding.FragmentBusinessBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusinessFragment : Fragment() {

    lateinit var businessFragmentBinding : FragmentBusinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        businessFragmentBinding = FragmentBusinessBinding.inflate(inflater,container,false)

        getCountry()

        return businessFragmentBinding.root
    }

    fun getCountry(){
        var apiInterface = APIClient().getRetrofoit().create(APIInterface::class.java)
        apiInterface.getCountry("in","business","532a522ab79744f7a07aec970a5bdeaf").enqueue(object : Callback<CountryModel>{
            override fun onResponse(call: Call<CountryModel>, response: Response<CountryModel>) {

                var list = response.body()?.articles!!

                businessFragmentBinding.shimmerEffect.isVisible = false

                setUpRV(list)

                Log.e("TAG", "onResponse: business ======= $list")
            }

            override fun onFailure(call: Call<CountryModel>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun setUpRV(l1: List<com.example.newsapp.ModelClassAPI.ArticlesItem?>) {

        var adapter = CountryAdapter(activity, l1)
        var layoutManager = LinearLayoutManager(activity)
        businessFragmentBinding.rvView.adapter = adapter
        businessFragmentBinding.rvView.layoutManager = layoutManager
    }

}