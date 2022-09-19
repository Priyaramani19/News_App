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
import com.example.newsapp.R
import com.example.newsapp.Retrofit.APIClient
import com.example.newsapp.Retrofit.APIInterface
import com.example.newsapp.databinding.ActivitySearchBinding
import com.example.newsapp.databinding.FragmentPoliticsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoliticsFragment : Fragment() {

    lateinit var politicsFragmentBinding: FragmentPoliticsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        politicsFragmentBinding = FragmentPoliticsBinding.inflate(inflater,container,false)

        getCountry()

        return politicsFragmentBinding.root
    }


    fun getCountry(){
        var apiInterface = APIClient().getRetrofoit().create(APIInterface::class.java)
        apiInterface.getCountry("in","politics","532a522ab79744f7a07aec970a5bdeaf").enqueue(object :
            Callback<CountryModel> {
            override fun onResponse(call: Call<CountryModel>, response: Response<CountryModel>) {

                var list = response.body()?.articles!!

                politicsFragmentBinding.shimmerEffect.isVisible = false

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
        politicsFragmentBinding.rvView.adapter = adapter
        politicsFragmentBinding.rvView.layoutManager = layoutManager
    }

}