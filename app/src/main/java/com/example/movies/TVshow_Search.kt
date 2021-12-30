package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.CompleteListAdapter
import com.example.movies.DataClasses.LinkData
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_movies_search.*
import kotlinx.android.synthetic.main.activity_tvshow_search.*
import kotlinx.android.synthetic.main.activity_tvshow_search.tablayoutitem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.Toolbar;

class TVshow_Search : AppCompatActivity() {

    lateinit var adapter: CompleteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_search)

        val searchInput = intent.getStringExtra("serach_input")
        val toolbar : Toolbar = findViewById(R.id.tvshowtoobar)
        setSupportActionBar(toolbar)
        toolbar.title = searchInput
        supportActionBar?.setHomeAsUpIndicator(R.drawable.backbutton)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tablayoutitem.selectTab(tablayoutitem.getTabAt(1))
        tablayoutitem.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tablayoutitem.selectedTabPosition == 0){
                    finish()
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
        val movies = ApiService.instance.getSearchTV(searchInput)
        movies.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                val getdata = response.body()
                if (getdata != null) {
                    Log.d("Usman_Msg: ", getdata.toString())
                    adapter = CompleteListAdapter(this@TVshow_Search, getdata.results)
                    searched_tvshow_recyclerview.adapter = adapter
                    searched_tvshow_recyclerview.adapter = adapter
                    searched_tvshow_recyclerview.layoutManager = LinearLayoutManager(this@TVshow_Search)

                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d("Usman_Msg: ", "Error Fetching Data", t)
            }
        })
    }
}