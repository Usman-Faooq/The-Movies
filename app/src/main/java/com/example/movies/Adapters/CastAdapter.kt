package com.example.movies.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.DataClasses.CastData
import com.example.movies.R

class CastAdapter(val context: Context, val castData: List<CastData>) :
    RecyclerView.Adapter<CastAdapter.MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movies_layout, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = castData[position]
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+data.profile_path).into(holder.movie_img)
        holder.movie_title.text = data.original_name
        holder.movie_name.text = data.character
    }

    override fun getItemCount(): Int {
        return castData.size
    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var movie_img = itemView.findViewById<ImageView>(R.id.movie_poster)
        var movie_title = itemView.findViewById<TextView>(R.id.movie_name)
        var movie_name = itemView.findViewById<TextView>(R.id.movie_detail)
    }
}