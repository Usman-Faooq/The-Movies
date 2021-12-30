package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movies.Adapters.CastAdapter
import com.example.movies.Adapters.TrailerAdapter
import com.example.movies.DataClasses.CastLink
import com.example.movies.DataClasses.MovieDetail
import com.example.movies.DataClasses.TrailerLink
import com.example.movies.RoomDB.Data
import com.example.movies.RoomDB.UserDataBase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_description.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDescription : AppCompatActivity() {

    lateinit var adapter: CastAdapter
    lateinit var trailerAdapter: TrailerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)


        val relase_date = intent.getStringExtra("DATE")
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
            getMovieDetail(id)
            getMovieTrailer(id, image!!, title!!, check)
        }

        //insert Movies in room
        fav_btn.setOnClickListener {
            var checkexistdata: Boolean = UserDataBase.getInstance(this@MovieDescription).userDao().checkExistingData(id)
            if (checkexistdata == true){
                //Show Message here
            }else{
                var mov_data = Data(id, title, image, overview, rating, check, relase_date)
                GlobalScope.launch {
                    UserDataBase.getInstance(this@MovieDescription).userDao().insertData(mov_data)
                }
                //Show Message here
            }
        }
    }

    private fun getMovieTrailer(id: Int, image: String, title: String, check: Int) {
        val trailer = ApiService.instance.getMovieTrailer(id)
        trailer.enqueue(object : Callback<TrailerLink>{
            override fun onResponse(call: Call<TrailerLink>, response: Response<TrailerLink>) {
                val getdata = response.body()
                if (response!= null){
                    Log.d("UGS_DATA: " , "Size: " + getdata!!.results.size)
                    trailerAdapter = TrailerAdapter(this@MovieDescription, getdata!!.results, image, id, title, check)
                    trailer_recyclerview.layoutManager = LinearLayoutManager(this@MovieDescription, LinearLayoutManager.HORIZONTAL, true)
                    trailer_recyclerview.adapter = trailerAdapter
                }
            }

            override fun onFailure(call: Call<TrailerLink>, t: Throwable) {

            }
        })
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

    private fun getMovieDetail(id: Int) {
        val detail = ApiService.instance.getMovieDetai(id)
        detail.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                val getdata = response.body()
                if (getdata != null){
                    moviestatus.text = getdata.status
                    moviebudget.text = "$"+getdata.budget.toString()+ ".00"
                    movierevenue.text = "$"+getdata.revenue.toString()+ ".00"
                    movieruntime.text = getdata.runtime.toString() + " min"
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {

            }
        })
    }

}
