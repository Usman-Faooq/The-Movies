package com.example.movies.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.DataClasses.MovieData
import com.example.movies.MovieDescription
import com.example.movies.R

class CompleteListAdapter(val context: Context, val movieData: List<MovieData>) :
    RecyclerView.Adapter<CompleteListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_paging_layout, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = movieData[position]

        //rating
        holder.searchrating.rating = data.vote_average/2
        holder.ratingtext.text = data.vote_average.toString() + "/10"

        val movie_title: String
        val check : Int // 0 for TV and 1 for Movies
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+data.poster_path).into(holder.movie_img)
        if (data.title == null){
            holder.movie_title.text = data.name
            holder.movie_date.text = data.first_air_date
            movie_title = data.name
            check = 0
        }else{
            holder.movie_title.text = data.title
            holder.movie_date.text = data.release_date
            movie_title = data.title
            check = 1
        }

        holder.clickcover.setOnClickListener {
            val intent = Intent(context, MovieDescription::class.java)
            intent.putExtra("movie_img", "https://image.tmdb.org/t/p/w500" + data.poster_path)
            intent.putExtra("movie_title", movie_title)
            val rating : Float = data.vote_average
            intent.putExtra("rating", rating)
            intent.putExtra("check", check)
            intent.putExtra("movie_id", data.id)
            intent.putExtra("movie_overview", data.overview)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieData.size
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