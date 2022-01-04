package com.example.movies.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.MovieDescription
import com.example.movies.R
import com.example.movies.RoomDB.Data
import com.example.movies.RoomDB.UserDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomDataAdapter(val context: Context, val data: List<Data>) :
    RecyclerView.Adapter<RoomDataAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_paging_layout, parent, false)
        return RoomDataAdapter.MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val getdata = data[position]

        val check: Int? = getdata.check_cat
        Glide.with(context).load(getdata.movie_image).into(holder.movie_img)
        holder.searchrating.rating = getdata.movie_rating!! /2
        holder.ratingtext.text = getdata.movie_rating.toString() + "/10"
        holder.movie_title.text = getdata.movie_title
        holder.movie_date.text = getdata.relase_date

        holder.close.setOnClickListener {
            GlobalScope.launch {
                UserDataBase.getInstance(context).userDao().deleteData(getdata.movie_id!!)
            }
            Toast.makeText(context, "Delete suc...", Toast.LENGTH_SHORT).show()
            (context as Activity).recreate()

        }

        holder.clickcover.setOnClickListener {
            val intent = Intent(context, MovieDescription::class.java)
            intent.putExtra("movie_img", getdata.movie_image)
            intent.putExtra("movie_title", getdata.movie_title)
            intent.putExtra("rating", getdata.movie_rating)
            intent.putExtra("check", check)
            intent.putExtra("DATE", getdata.relase_date)
            intent.putExtra("movie_id", getdata.movie_id)
            intent.putExtra("movie_overview", getdata.movie_overview)
            context.startActivity(intent)
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
        var close = itemView.findViewById<ImageView>(R.id.remove_fav)
        var searchrating = itemView.findViewById<RatingBar>(R.id.search_movie_rating)
        var ratingtext = itemView.findViewById<TextView>(R.id.search_ratingtext)
    }


}