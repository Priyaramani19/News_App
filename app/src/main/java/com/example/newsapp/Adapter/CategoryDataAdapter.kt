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
import com.example.newsapp.ArticlesItem
import com.example.newsapp.R
import com.example.newsapp.Screen.CategoryDataActivity
import com.example.newsapp.Screen.DataActivity

class CategoryDataAdapter(
    val categoryDataActivity: CategoryDataActivity,
    val list: List<ArticlesItem?>
) : RecyclerView.Adapter<CategoryDataAdapter.Viewholder>() {

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.image)
        var titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
        var discriptionTxt = itemView.findViewById<TextView>(R.id.discriptionTxt)
        var viewContainer = itemView.findViewById<RelativeLayout>(R.id.viewContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var view = LayoutInflater.from(categoryDataActivity)
            .inflate(R.layout.category_data_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        Glide.with(categoryDataActivity).load(list.get(position)?.urlToImage).placeholder(R.drawable.placeholder).centerCrop().into(holder.image)

        holder.titleTxt.text = list.get(position)?.title
        holder.discriptionTxt.text = list.get(position)?.description

        holder.viewContainer.setOnClickListener {
            var intent = Intent(categoryDataActivity,DataActivity::class.java)
            intent.putExtra("sourceName",list[position]?.source?.name)
            intent.putExtra("title",list[position]?.title)
            intent.putExtra("urlToImage",list[position]?.urlToImage)
            intent.putExtra("publishedAt",list[position]?.publishedAt)
            intent.putExtra("description",list[position]?.description)
            intent.putExtra("url",list[position]?.url)
            categoryDataActivity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
