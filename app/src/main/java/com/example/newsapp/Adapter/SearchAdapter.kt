package com.example.newsapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.Screen.CategoryDataActivity
import com.example.newsapp.Screen.SearchActivity

class SearchAdapter(
    val searchActivity: SearchActivity,
    var searchlist: ArrayList<String>,
    val channelImageList: Array<String>,
    val sourceList: ArrayList<String>
) : RecyclerView.Adapter<SearchAdapter.Viewholder>() {

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.name)
        var channelImg = itemView.findViewById<ImageView>(R.id.channelImg)
        var container = itemView.findViewById<RelativeLayout>(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var view = LayoutInflater.from(searchActivity).inflate(R.layout.search_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.name.text = searchlist[position]

        searchActivity?.let {
            Glide.with(it).load(channelImageList[position]).centerCrop()
                .placeholder(R.drawable.placeholder).into(holder.channelImg)
        }

        holder.container.setOnClickListener {
            var intent = Intent(searchActivity,CategoryDataActivity::class.java)
            intent.putExtra("name",sourceList[position])
            intent.putExtra("sourceName",searchlist[position])
            searchActivity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return searchlist.size
    }

    fun filterSearch(filterSearchlist: ArrayList<String>) {

        searchlist = filterSearchlist
        notifyDataSetChanged()

    }

}
