package com.example.newsapp.FragmentActivity

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter.GNewsINAdapter
import com.example.newsapp.ArticlesItem
import com.example.newsapp.GNewsINModel
import com.example.newsapp.Retrofit.APIClient
import com.example.newsapp.Retrofit.APIInterface
import com.example.newsapp.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {


    lateinit var mainFragmentBinding: FragmentMainBinding
    var monthName = arrayOf(
        "January", "February",
        "March", "April", "May", "June", "July",
        "August", "September", "October", "November",
        "December"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false)

        setDate()
        getGNewsIN()
        initClick()

        return mainFragmentBinding.root

    }

    private fun setDate() {

        val calendar = Calendar.getInstance()
        val month = monthName[calendar.get(Calendar.MONTH)]
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        mainFragmentBinding.date.text = "$day\t$month,"

        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)

        mainFragmentBinding.day.text = dayOfTheWeek
    }

    private fun initClick() {

        mainFragmentBinding.menuImg.setOnClickListener {
            mainFragmentBinding.drawerView.openDrawer(GravityCompat.START)
        }

    }

    fun getGNewsIN() {

        var apiInterface = APIClient().getRetrofoit().create(APIInterface::class.java)

        apiInterface.getGNewsIN("google-news-in", "532a522ab79744f7a07aec970a5bdeaf")
            .enqueue(object : Callback<GNewsINModel> {
                override fun onResponse(
                    call: Call<GNewsINModel>,
                    response: Response<GNewsINModel>
                ) {

                    var list = response.body()?.articles!!

                    mainFragmentBinding.shimmerEffect.isVisible = false

                    setUpRV(list)

                    Log.e("TAG", "onResponse: =============$list")

                }

                override fun onFailure(call: Call<GNewsINModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}")
                }
            })

    }

    fun setUpRV(l1: List<ArticlesItem?>) {

        var adapter = GNewsINAdapter(activity, l1)
        var layoutManager = LinearLayoutManager(activity)
        mainFragmentBinding.rvView.adapter = adapter
        mainFragmentBinding.rvView.layoutManager = layoutManager
    }

}