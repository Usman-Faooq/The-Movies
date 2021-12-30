package com.example.movies.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.DataClasses.GetTrailerData
import com.example.movies.R
import com.example.movies.RoomDB.UserDataBase
import com.example.movies.RoomDB.WatchListData
import com.example.movies.VideoPlayerActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrailerAdapter(val context: Context,
                     val trailerData: List<GetTrailerData>,
                     val trailerimage : String, val id : Int,
                     val movie_title: String, val check: Int)
    : RecyclerView.Adapter<TrailerAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.trailer_layout, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Glide.with(context).load(trailerimage).into(holder.movie_img)

        val data = trailerData[position]


            holder.trailer_name.text = data.name
            val video_key : String = data.key
            val trailer_id : String = data.id

            holder.clickcover.setOnClickListener {

                //add data to watchlist in room database
                var checkexistdata = UserDataBase.getInstance(context).userDao().checkWatchListExisting(id, data.id)
                if (checkexistdata == true){
                    //Show Message here
                }else{
                    var watchlistdata = WatchListData(id,data.id,movie_title,data.name,check, trailerimage)
                    GlobalScope.launch {
                        UserDataBase.getInstance(context).userDao().insertWatchData(watchlistdata)
                    }
                    //Show Message here
                }

                val intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("video_key", video_key)
                intent.putExtra("trailer_name", data.name)
                context.startActivity(intent)
            }

    }

    override fun getItemCount(): Int {
        return trailerData.size
    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var clickcover = itemView.findViewById<CardView>(R.id.trailer_card_view_simple)
        var movie_img = itemView.findViewById<ImageView>(R.id.trailer_poster)
        var trailer_name = itemView.findViewById<TextView>(R.id.tralier_name)
    }
}