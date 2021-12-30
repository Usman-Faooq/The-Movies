package com.example.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.CompleteListAdapter
import com.example.movies.DataClasses.LinkData
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movies_search.*
import kotlinx.android.synthetic.main.activity_movies_search.tablayoutitem
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_tvshow_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies_Search : AppCompatActivity() {

    lateinit var adapter: CompleteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_search)

        val searchInput = intent.getStringExtra("serach_input")
        val toolbar : Toolbar = findViewById(R.id.movietoobar)
        setSupportActionBar(toolbar)
        toolbar.title = searchInput
        supportActionBar?.setHomeAsUpIndicator(R.drawable.backbutton)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tablayoutitem.selectTab(tablayoutitem.getTabAt(0))

        tablayoutitem.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tablayoutitem.selectedTabPosition == 0){

                }else if (tablayoutitem.selectedTabPosition == 1){
                    tablayoutitem.selectTab(tablayoutitem.getTabAt(0))
                    val intent = Intent(this@Movies_Search, TVshow_Search::class.java)
                    intent.putExtra("serach_input", searchInput)
                    startActivity(intent)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        getSearchedMovies(searchInput.toString())


    }

    private fun getSearchedMovies(searchInput: String) {
        val movies = ApiService.instance.getSearchMovies(searchInput)
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = CompleteListAdapter(this@Movies_Search, getdata.results)
                    searched_movies_recyclerview.adapter = adapter
                    searched_movies_recyclerview.adapter = adapter
                    searched_movies_recyclerview.layoutManager = LinearLayoutManager(this@Movies_Search)

                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }
}