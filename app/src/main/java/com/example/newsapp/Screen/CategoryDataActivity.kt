package com.example.newsapp.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter.CategoryDataAdapter
import com.example.newsapp.ArticlesItem
import com.example.newsapp.GNewsINModel
import com.example.newsapp.Retrofit.APIClient
import com.example.newsapp.Retrofit.APIInterface
import com.example.newsapp.databinding.ActivityCategoryDataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDataActivity : AppCompatActivity() {

    lateinit var name: String
    lateinit var sourceName: String
    lateinit var categoryDataBinding: ActivityCategoryDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryDataBinding = ActivityCategoryDataBinding.inflate(layoutInflater)
        setContentView(categoryDataBinding.root)

        name = intent.getStringExtra("name").toString()
        sourceName = intent.getStringExtra("sourceName").toString()
        categoryDataBinding.sourceName.text = sourceName
        getSource()
        initClick()

    }

    private fun initClick() {
        categoryDataBinding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun getSource() {

        var apiInterface = APIClient().getRetrofoit().create(APIInterface::class.java)
        apiInterface.getGNewsIN("$name", "532a522ab79744f7a07aec970a5bdeaf")
            .enqueue(object : Callback<GNewsINModel> {
                override fun onResponse(
                    call: Call<GNewsINModel>,
                    response: Response<GNewsINModel>
                ) {
                    var list = response.body()?.articles!!

                    setupRV(list)

                    Log.e("TAG", "onResponse: top News ======= $list")
                }

                override fun onFailure(call: Call<GNewsINModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }

    fun setupRV(list: List<ArticlesItem?>) {

        var adapter = CategoryDataAdapter(this,list)
        var layoutManager = LinearLayoutManager(this)
        categoryDataBinding.rvView.adapter = adapter
        categoryDataBinding.rvView.layoutManager = layoutManager

    }
}