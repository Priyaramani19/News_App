package com.example.newsapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.ArticlesItem
import com.example.newsapp.FragmentActivity.MainFragment
import com.example.newsapp.R
import com.example.newsapp.Screen.DataActivity
import com.example.newsapp.Screen.MainActivity

class GNewsINAdapter(val mainFragment: FragmentActivity?, val l1: List<ArticlesItem?>) :
    RecyclerView.Adapter<GNewsINAdapter.Viewholder>() {

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author = itemView.findViewById<TextView>(R.id.author)
        var title = itemView.findViewById<TextView>(R.id.title)
        var discription = itemView.findViewById<TextView>(R.id.discription)
        var image = itemView.findViewById<ImageView>(R.id.image)
        var container = itemView.findViewById<RelativeLayout>(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var view = LayoutInflater.from(mainFragment).inflate(R.layout.gnews_in_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        var author = l1.get(position)?.author

        holder.author.text = author as CharSequence?
        holder.title.text = l1.get(position)?.title
        holder.discription.text = l1.get(position)?.description

        mainFragment?.let {
            Glide.with(it).load(l1?.get(position)?.urlToImage).placeholder(R.drawable.placeholder)
                .into(holder.image)
        }

        holder.container.setOnClickListener {

            var intent = Intent(mainFragment, DataActivity::class.java)
            intent.putExtra("sourceName",l1[position]?.source?.name)
            intent.putExtra("title",l1[position]?.title)
            intent.putExtra("urlToImage",l1[position]?.urlToImage)
            intent.putExtra("publishedAt",l1[position]?.publishedAt)
            intent.putExtra("description",l1[position]?.description)
            intent.putExtra("url",l1[position]?.url)
            mainFragment?.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return l1.size
    }

}

