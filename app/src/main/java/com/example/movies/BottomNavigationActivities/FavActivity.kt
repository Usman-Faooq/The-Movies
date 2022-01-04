package com.example.movies.BottomNavigationActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.RoomDataAdapter
import com.example.movies.BottomNavigationActivities.MoreActivities.CollectionActivity
import com.example.movies.BottomNavigationActivities.MoreActivities.WatchListActivity
import com.example.movies.R
import com.example.movies.RoomDB.Data
import com.example.movies.RoomDB.UserDataBase
import com.example.movies.RoomDB.WatchListData
import kotlinx.android.synthetic.main.activity_fav.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomnavigation

class FavActivity : AppCompatActivity() {

    lateinit var adapter : RoomDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        bottomnavigation.selectedItemId = R.id.activity_act
        bottomnavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_act ->{
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.search_act ->{
                    val intent = Intent(this, Search::class.java);
                    overridePendingTransition(0,0)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.activity_act ->{
                    return@setOnNavigationItemSelectedListener true
                }
            }
            true
        }

        watchlistbtn.setOnClickListener {
            var intent = Intent(this, WatchListActivity::class.java)
            startActivity(intent)
        }

        collectionbtn.setOnClickListener {
            var intent = Intent(this, CollectionActivity::class.java)
            startActivity(intent)
        }


        getData()

    }

    private fun getData() {
        val list: List<Data> = UserDataBase.getInstance(this@FavActivity).userDao().displayData()
        if (list != null){
            adapter = RoomDataAdapter(this, list)
            fav_recyclerview.adapter = adapter
            fav_recyclerview.layoutManager = LinearLayoutManager(this@FavActivity)
            fav_recyclerview.setHasFixedSize(false)
        }else{

        }
    }
}