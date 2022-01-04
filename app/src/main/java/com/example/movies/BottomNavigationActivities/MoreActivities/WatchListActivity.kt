package com.example.movies.BottomNavigationActivities.MoreActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.RoomDataAdapter
import com.example.movies.Adapters.WatchListAdapter
import com.example.movies.R
import com.example.movies.RoomDB.UserDataBase
import com.example.movies.RoomDB.WatchListData
import kotlinx.android.synthetic.main.activity_watch_list.*

class WatchListActivity : AppCompatActivity() {

    lateinit var adapter : WatchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_list)

        favbtn.setOnClickListener {
            finish()
        }

        collbtn.setOnClickListener {
            var intent = Intent(this, CollectionActivity::class.java)
            startActivity(intent)
        }

        getdata()
    }

    private fun getdata() {
        val list: List<WatchListData> = UserDataBase.getInstance(this).userDao().displayWatchList()
        if (list != null){
            adapter = WatchListAdapter(this, list)
            watchlist_recyclerview.adapter = adapter
            watchlist_recyclerview.layoutManager = LinearLayoutManager(this@WatchListActivity)
            watchlist_recyclerview.setHasFixedSize(false)

        }else{

        }
    }
}