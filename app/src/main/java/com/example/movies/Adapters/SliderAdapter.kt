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
import com.bumptech.glide.Glide
import com.example.movies.DataClasses.MovieData
import com.example.movies.MovieDescription
import com.example.movies.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(var context: Context, var movielist : List<MovieData>) : SliderViewAdapter<SliderAdapter.MyHolder>() {

    override fun getCount(): Int {
        return movielist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): MyHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.slider_layout, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyHolder?, position: Int) {
        val data = movielist[position]

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+data.poster_path).into(viewHolder!!.movieimage)
        viewHolder.moviename.text = data.title
        viewHolder.moviedate.text = data.release_date
        viewHolder.ratingstars.rating = data.vote_average/2
        viewHolder.ratingtext.text = data.vote_average.toString() + "/10"

        viewHolder.click.setOnClickListener {
            val intent = Intent(context, MovieDescription::class.java)
            intent.putExtra("movie_img", "https://image.tmdb.org/t/p/w500" + data.poster_path)
            intent.putExtra("movie_title", data.title)
            val rating : Float = data.vote_average
            intent.putExtra("rating", rating)
            intent.putExtra("check", 1)
            intent.putExtra("DATE", data.release_date)
            intent.putExtra("movie_id", data.id)
            intent.putExtra("movie_overview", data.overview)
            context.startActivity(intent)
        }


    }


    class MyHolder(itemview : View) : SliderViewAdapter.ViewHolder(itemview) {

        var click = itemview.findViewById<RelativeLayout>(R.id.click_layout)
        var movieimage = itemView.findViewById<ImageView>(R.id.slider_movie_image)
        var moviename = itemView.findViewById<TextView>(R.id.slider_movie_name)
        var moviedate = itemView.findViewById<TextView>(R.id.slider_movie_date)
        var ratingstars = itemView.findViewById<RatingBar>(R.id.slider_movie_rating)
        var ratingtext = itemView.findViewById<TextView>(R.id.slider_ratingtext)
    }

}