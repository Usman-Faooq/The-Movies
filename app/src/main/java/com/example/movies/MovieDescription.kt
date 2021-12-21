package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movies.Adapters.CastAdapter
import com.example.movies.DataClasses.CastLink
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_description.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDescription : AppCompatActivity() {
    lateinit var adapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)



        val title = intent.getStringExtra("movie_title")
        val image = intent.getStringExtra("movie_img")
        val overview = intent.getStringExtra("movie_overview")
        val rating = intent.getFloatExtra("rating", 0.0F)
        val id = intent.getIntExtra("movie_id", 0)
        val check = intent.getIntExtra("check", 0);
        Glide.with(this).load(image).into(movie_poster)
        Glide.with(this).load(image).into(back_poster_img)
        ratingtext.text = rating.toString() + "/10"
        movie_title.text = title
        movie_overview.text = overview
        movie_rating.rating = rating/2

        if (check == 0){
            getTVCast(id)
        }else{
            getMovieCast(id)
        }

    }

    private fun getTVCast(id: Int) {

        val cast = ApiService.instance.getTVCast(id)
        cast.enqueue(object : Callback<CastLink>{
            override fun onResponse(call: Call<CastLink>, response: Response<CastLink>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = CastAdapter(this@MovieDescription, getdata.cast)
                    cast_recyclerview.adapter = adapter
                    cast_recyclerview.layoutManager = LinearLayoutManager(this@MovieDescription, LinearLayoutManager.HORIZONTAL, true)
                    cast_recyclerview.setHasFixedSize(true)
                }

            }

            override fun onFailure(call: Call<CastLink>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun getMovieCast(id: Int) {
        val cast = ApiService.instance.getMovieCast(id)
        cast.enqueue(object : Callback<CastLink>{
            override fun onResponse(call: Call<CastLink>, response: Response<CastLink>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = CastAdapter(this@MovieDescription, getdata.cast)
                    cast_recyclerview.adapter = adapter
                    cast_recyclerview.layoutManager = LinearLayoutManager(this@MovieDescription, LinearLayoutManager.HORIZONTAL, true)
                    cast_recyclerview.setHasFixedSize(true)
                }

            }

            override fun onFailure(call: Call<CastLink>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
