package com.example.movies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.MoviesAdapter
import com.example.movies.DataClasses.LinkData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TV Shows....
        getTopRatedTVSHow()
        getPopularTVShows()

        //Movies....
        getInTheaterMovies()
        getPopularMovies()
        getUpcomingMovies()
        getTopRatedMovies()

    }

    private fun getTopRatedTVSHow() {
        val movies = ApiService.instance.getTopRatedTVShow()
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = MoviesAdapter(this@MainActivity, getdata.results)
                    top_tvshow_recyclerview.adapter = adapter
                    top_tvshow_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    top_tvshow_recyclerview.setHasFixedSize(false)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }

    private fun getPopularTVShows() {
        val movies = ApiService.instance.getPopularTVShow()
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = MoviesAdapter(this@MainActivity, getdata.results)
                    pop_tvshow_recyclerview.adapter = adapter
                    pop_tvshow_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    pop_tvshow_recyclerview.setHasFixedSize(false)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }


    private fun getInTheaterMovies() {
        val movies = ApiService.instance.getInTheaterMovies()
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = MoviesAdapter(this@MainActivity, getdata.results)
                    theater_recyclerview.adapter = adapter
                    theater_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    theater_recyclerview.setHasFixedSize(false)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }

    private fun getPopularMovies() {
        val movies = ApiService.instance.getPopularMovies()
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = MoviesAdapter(this@MainActivity, getdata.results)
                    movie_recyclerview.adapter = adapter
                    movie_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    movie_recyclerview.setHasFixedSize(false)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }

    private fun getUpcomingMovies() {
        val movies = ApiService.instance.getUpcomingMovies()
        movies.enqueue(object : Callback<LinkData>{
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val data = response.body()
                if (data != null){
                    adapter = MoviesAdapter(this@MainActivity, data.results)
                    upcomingmovie_recyclerview.adapter = adapter
                    upcomingmovie_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    upcomingmovie_recyclerview.setHasFixedSize(false)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }

    private fun getTopRatedMovies() {
        val movies = ApiService.instance.gettopRatedMovies()
        movies.enqueue(object : Callback<LinkData>{
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val data = response.body()
                if (data != null){
                    adapter = MoviesAdapter(this@MainActivity, data.results)
                    toprated_recyclerview.adapter = adapter
                    toprated_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    toprated_recyclerview.setHasFixedSize(false)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }
}