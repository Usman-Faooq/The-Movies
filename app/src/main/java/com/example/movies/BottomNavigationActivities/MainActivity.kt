package com.example.movies.BottomNavigationActivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.movies.Adapters.MoviesAdapter
import com.example.movies.Adapters.SliderAdapter
import com.example.movies.ApiService
import com.example.movies.DataClasses.LinkData
import com.example.movies.DataClasses.MovieData
import com.example.movies.MoviesCompleteList
import com.example.movies.R
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MoviesAdapter
    lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomnavigation.selectedItemId = R.id.home_act
        bottomnavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_act ->{
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.search_act ->{
                    val intent = Intent(this, Search::class.java);
                    overridePendingTransition(0,0)
                    startActivity(intent)
                    bottomnavigation.selectedItemId = R.id.home_act
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.activity_act ->{
                    val intent = Intent(this, FavActivity::class.java);
                    overridePendingTransition(0,0)
                    startActivity(intent)
                    bottomnavigation.selectedItemId = R.id.home_act
                    return@setOnNavigationItemSelectedListener true
                }
            }
            true
        }


        more_theater.setOnClickListener {
            val intent = Intent(this, MoviesCompleteList::class.java)
            intent.putExtra("movie_category", "movie_now_playing")
            startActivity(intent)
        }

        more_pop_tvshow.setOnClickListener {
            val intent = Intent(this, MoviesCompleteList::class.java)
            intent.putExtra("movie_category", "tv_popular")
            startActivity(intent)
        }

        more_toprated_tvshow.setOnClickListener {
            val intent = Intent(this, MoviesCompleteList::class.java)
            intent.putExtra("movie_category", "tv_top_rated")
            startActivity(intent)
        }

        more_pop_movies.setOnClickListener {
            val intent = Intent(this, MoviesCompleteList::class.java)
            intent.putExtra("movie_category", "movie_popular")
            startActivity(intent)
        }

        more_upcoming_movies.setOnClickListener {
            val intent = Intent(this, MoviesCompleteList::class.java)
            intent.putExtra("movie_category", "movie_upcoming")
            startActivity(intent)
        }

        more_toprated_movies.setOnClickListener {
            val intent = Intent(this, MoviesCompleteList::class.java)
            intent.putExtra("movie_category", "movie_top_rated")
            startActivity(intent)
        }

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
        val movies = ApiService.instance.getTopRatedTVShow(1)
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
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
        val movies = ApiService.instance.getPopularTVShow(1)
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
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
        val movies = ApiService.instance.getInTheaterMovies(1)
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    adapter = MoviesAdapter(this@MainActivity, getdata.results)
                    theater_recyclerview.adapter = adapter
                    theater_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                    theater_recyclerview.setHasFixedSize(false)


                    //set Slider
                    sliderAdapter = SliderAdapter(this@MainActivity, getdata.results.subList(0,3))
                    imageSlider.setSliderAdapter(sliderAdapter)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }

    private fun getPopularMovies() {
        val movies = ApiService.instance.getPopularMovies(1)
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
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
        val movies = ApiService.instance.getUpcomingMovies(1)
        movies.enqueue(object : Callback<LinkData> {
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
        val movies = ApiService.instance.gettopRatedMovies(1)
        movies.enqueue(object : Callback<LinkData> {
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