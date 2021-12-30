package com.example.movies

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Adapters.CompleteListAdapter
import com.example.movies.DataClasses.LinkData
import com.example.movies.DataClasses.MovieData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movies_complete_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesCompleteList : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var movielist: MutableList<MovieData>
    lateinit var adapter: CompleteListAdapter
    var isScroll : Boolean = false
    var page: Int = 1

    var currentitem: Int = 0
    var totalitem: Int = 0
    var scrolloutitem: Int = 0
    var lastitem: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_complete_list)
        layoutManager = LinearLayoutManager(this@MoviesCompleteList)

        val category = intent.getStringExtra("movie_category")

        if (category == "movie_now_playing"){
            getInTheaterMovies()
        }else if (category == "tv_popular") {
            getTVpopular()
        }else if (category == "tv_top_rated"){
            getTVRated()
        }else if (category == "movie_popular") {
            getPopuarMovies()
        }else if (category == "movie_upcoming") {
            getUpcomingMovies()
        }else if (category == "movie_top_rated"){
            getTopRatedMovies()
        }

    }

    private fun getTopRatedMovies() {
        val movies = ApiService.instance.gettopRatedMovies(page)

        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()

                if (getdata != null) {
                    movielist = getdata.results as MutableList<MovieData>
                    Log.d("UGS_DATA: ", "data: " + movielist.size)

                    adapter = CompleteListAdapter(this@MoviesCompleteList, movielist)
                    completelist_recyclerview.adapter = adapter
                    completelist_recyclerview.layoutManager = layoutManager
                    val scroll = completelist_recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                isScroll = true
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            currentitem = layoutManager.childCount
                            totalitem = layoutManager.itemCount
                            scrolloutitem = layoutManager.findFirstVisibleItemPosition()
                            if (isScroll && (currentitem + scrolloutitem == totalitem) && lastitem == false){
                                isScroll = false
                                page++
                                val nextlist = ApiService.instance.gettopRatedMovies(page)
                                nextlist.enqueue(object : Callback<LinkData>{
                                    override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                                        val getnextdata = response.body()
                                        if (getnextdata!=null){
                                            val list : MutableList<MovieData> = getnextdata.results as MutableList<MovieData>

                                            movielist.addAll(list)
                                            adapter.notifyDataSetChanged()
                                            Log.d("UGS_DATA: ", "Size After Scroll: " + movielist.size)

                                            if (list.size < 20){
                                                lastitem = true
                                            }

                                        }
                                    }

                                    override fun onFailure(call: Call<LinkData>, t: Throwable) {

                                    }
                                })
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)

            }
        })
    }

    private fun getUpcomingMovies() {
        val movies = ApiService.instance.getUpcomingMovies(page)

        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()

                if (getdata != null) {
                    movielist = getdata.results as MutableList<MovieData>
                    Log.d("UGS_DATA: ", "data: " + movielist.size)

                    adapter = CompleteListAdapter(this@MoviesCompleteList, movielist)
                    completelist_recyclerview.adapter = adapter
                    completelist_recyclerview.layoutManager = layoutManager
                    val scroll = completelist_recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                isScroll = true
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            currentitem = layoutManager.childCount
                            totalitem = layoutManager.itemCount
                            scrolloutitem = layoutManager.findFirstVisibleItemPosition()
                            if (isScroll && (currentitem + scrolloutitem == totalitem) && lastitem == false){
                                isScroll = false
                                page++
                                val nextlist = ApiService.instance.getUpcomingMovies(page)
                                nextlist.enqueue(object : Callback<LinkData>{
                                    override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                                        val getnextdata = response.body()
                                        if (getnextdata!=null){
                                            val list : MutableList<MovieData> = getnextdata.results as MutableList<MovieData>

                                            movielist.addAll(list)
                                            adapter.notifyDataSetChanged()
                                            Log.d("UGS_DATA: ", "Size After Scroll: " + movielist.size)

                                            if (list.size < 20){
                                                lastitem = true
                                            }

                                        }
                                    }

                                    override fun onFailure(call: Call<LinkData>, t: Throwable) {

                                    }
                                })
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)

            }
        })
    }

    private fun getPopuarMovies() {
        val movies = ApiService.instance.getPopularMovies(page)

        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()

                if (getdata != null) {
                    movielist = getdata.results as MutableList<MovieData>
                    Log.d("UGS_DATA: ", "data: " + movielist.size)

                    adapter = CompleteListAdapter(this@MoviesCompleteList, movielist)
                    completelist_recyclerview.adapter = adapter
                    completelist_recyclerview.layoutManager = layoutManager
                    val scroll = completelist_recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                isScroll = true
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            currentitem = layoutManager.childCount
                            totalitem = layoutManager.itemCount
                            scrolloutitem = layoutManager.findFirstVisibleItemPosition()
                            if (isScroll && (currentitem + scrolloutitem == totalitem) && lastitem == false){
                                isScroll = false
                                page++
                                val nextlist = ApiService.instance.getPopularMovies(page)
                                nextlist.enqueue(object : Callback<LinkData>{
                                    override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                                        val getnextdata = response.body()
                                        if (getnextdata!=null){
                                            val list : MutableList<MovieData> = getnextdata.results as MutableList<MovieData>

                                            movielist.addAll(list)
                                            adapter.notifyDataSetChanged()
                                            Log.d("UGS_DATA: ", "Size After Scroll: " + movielist.size)

                                            if (list.size < 20){
                                                lastitem = true
                                            }

                                        }
                                    }

                                    override fun onFailure(call: Call<LinkData>, t: Throwable) {

                                    }
                                })
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)

            }
        })
    }

    private fun getTVRated() {
        val movies = ApiService.instance.getTopRatedTVShow(page)

        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()

                if (getdata != null) {
                    movielist = getdata.results as MutableList<MovieData>
                    Log.d("UGS_DATA: ", "data: " + movielist.size)

                    adapter = CompleteListAdapter(this@MoviesCompleteList, movielist)
                    completelist_recyclerview.adapter = adapter
                    completelist_recyclerview.layoutManager = layoutManager
                    val scroll = completelist_recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                isScroll = true
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            currentitem = layoutManager.childCount
                            totalitem = layoutManager.itemCount
                            scrolloutitem = layoutManager.findFirstVisibleItemPosition()
                            if (isScroll && (currentitem + scrolloutitem == totalitem) && lastitem == false){
                                isScroll = false
                                page++
                                val nextlist = ApiService.instance.getTopRatedTVShow(page)
                                nextlist.enqueue(object : Callback<LinkData>{
                                    override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                                        val getnextdata = response.body()
                                        if (getnextdata!=null){
                                            val list : MutableList<MovieData> = getnextdata.results as MutableList<MovieData>

                                            movielist.addAll(list)
                                            adapter.notifyDataSetChanged()
                                            Log.d("UGS_DATA: ", "Size After Scroll: " + movielist.size)

                                            if (list.size < 20){
                                                lastitem = true
                                            }

                                        }
                                    }

                                    override fun onFailure(call: Call<LinkData>, t: Throwable) {

                                    }
                                })
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)

            }
        })
    }

    private fun getTVpopular() {
        val movies = ApiService.instance.getPopularTVShow(page)

        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()

                if (getdata != null) {
                    movielist = getdata.results as MutableList<MovieData>
                    Log.d("UGS_DATA: ", "data: " + movielist.size)

                    adapter = CompleteListAdapter(this@MoviesCompleteList, movielist)
                    completelist_recyclerview.adapter = adapter
                    completelist_recyclerview.layoutManager = layoutManager
                    val scroll = completelist_recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                isScroll = true
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            currentitem = layoutManager.childCount
                            totalitem = layoutManager.itemCount
                            scrolloutitem = layoutManager.findFirstVisibleItemPosition()
                            if (isScroll && (currentitem + scrolloutitem == totalitem) && lastitem == false){
                                isScroll = false
                                page++
                                val nextlist = ApiService.instance.getPopularTVShow(page)
                                nextlist.enqueue(object : Callback<LinkData>{
                                    override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                                        val getnextdata = response.body()
                                        if (getnextdata!=null){
                                            val list : MutableList<MovieData> = getnextdata.results as MutableList<MovieData>

                                            movielist.addAll(list)
                                            adapter.notifyDataSetChanged()
                                            Log.d("UGS_DATA: ", "Size After Scroll: " + movielist.size)

                                            if (list.size < 20){
                                                lastitem = true
                                            }

                                        }
                                    }

                                    override fun onFailure(call: Call<LinkData>, t: Throwable) {

                                    }
                                })
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)

            }
        })

    }

    private fun getInTheaterMovies() {
        val movies = ApiService.instance.getInTheaterMovies(page)

        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()

                if (getdata != null) {
                    movielist = getdata.results as MutableList<MovieData>
                    Log.d("UGS_DATA: ", "data: " + movielist.size)

                    adapter = CompleteListAdapter(this@MoviesCompleteList, movielist)
                    completelist_recyclerview.adapter = adapter
                    completelist_recyclerview.layoutManager = layoutManager
                    val scroll = completelist_recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                    isScroll = true
                                }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            currentitem = layoutManager.childCount
                            totalitem = layoutManager.itemCount
                            scrolloutitem = layoutManager.findFirstVisibleItemPosition()
                            if (isScroll && (currentitem + scrolloutitem == totalitem) && lastitem == false){
                                isScroll = false
                                page++
                                val nextlist = ApiService.instance.getInTheaterMovies(page)
                                nextlist.enqueue(object : Callback<LinkData>{
                                    override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                                         val getnextdata = response.body()
                                        if (getnextdata!=null){
                                            val list : MutableList<MovieData> = getnextdata.results as MutableList<MovieData>

                                            movielist.addAll(list)
                                            adapter.notifyDataSetChanged()
                                            Log.d("UGS_DATA: ", "Size After Scroll: " + movielist.size)

                                            if (list.size < 20){
                                                lastitem = true
                                            }

                                        }
                                    }

                                    override fun onFailure(call: Call<LinkData>, t: Throwable) {

                                    }
                                })
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)

            }
        })

    }
}

