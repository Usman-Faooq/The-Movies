package com.example.movies.BottomNavigationActivities.MoreActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.CollectionsAdapter
import com.example.movies.BottomNavigationActivities.FavActivity
import com.example.movies.BottomNavigationActivities.MainActivity
import com.example.movies.R
import com.example.movies.RoomDB.CollectionData
import com.example.movies.RoomDB.UserDataBase
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_movie_description.*
import kotlinx.android.synthetic.main.activity_movie_description.collectionrecyclerview

class CollectionActivity : AppCompatActivity() {

    lateinit var collectionsAdapter: CollectionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        collection_favbtn.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        collection_watchlistbtn.setOnClickListener {
            finish()
        }


        val list: List<CollectionData> = UserDataBase.getInstance(this).userDao().displayCollections()
        if (list != null){

            collectionsAdapter = CollectionsAdapter(this,
                list, "ActivityOnClick",0,"title","image","overview", 0F, 0, "relase_date")
            coll_recyclerview.adapter = collectionsAdapter
            coll_recyclerview.layoutManager = LinearLayoutManager(this)

        }


    }
}