package com.example.movies.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.RoomDB.Data
import com.example.movies.RoomDB.WatchListData

class WatchListAdapter(val context: Context, val data: List<WatchListData>)
    : RecyclerView.Adapter<WatchListAdapter.MyHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_paging_layout, parent, false)
        return WatchListAdapter.MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val getdata = data[position]

        holder.movie_date.isVisible = false
        holder.searchrating.isVisible = false
        holder.ratingtext.isVisible = false

        Glide.with(context).load(getdata.trailerimage).into(holder.movie_img)
        holder.movie_title.text = getdata.trailer_title
        holder.clickcover.setOnClickListener {
            Toast.makeText(context, "Name : " + getdata.trailer_title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var clickcover = itemView.findViewById<RelativeLayout>(R.id.paging_click_cover)
        var movie_img = itemView.findViewById<ImageView>(R.id.movie_img)
        var movie_title = itemView.findViewById<TextView>(R.id.moviename)
        var movie_date = itemView.findViewById<TextView>(R.id.moviedate)
        var searchrating = itemView.findViewById<RatingBar>(R.id.search_movie_rating)
        var ratingtext = itemView.findViewById<TextView>(R.id.search_ratingtext)
    }

}