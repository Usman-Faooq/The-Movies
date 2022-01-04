package com.example.movies.BottomNavigationActivities.MoreActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.Adapters.CollectionsAdapter
import com.example.movies.R
import com.example.movies.RoomDB.CollectionData
import com.example.movies.RoomDB.UserDataBase
import kotlinx.android.synthetic.main.activity_collection_movies_list.*

class CollectionMoviesList : AppCompatActivity() {

    lateinit var collectionsAdapter: CollectionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_movies_list)

        var collectionName: String? = intent.getStringExtra("CollectionName")
        coll_name.text = collectionName

        val list: List<CollectionData> = UserDataBase.getInstance(this).userDao().displayAll(collectionName.toString())
        if (list != null){
            collectionsAdapter = CollectionsAdapter(this,
                list, "getCollectionData",0,"title","image","overview", 0F, 0, "relase_date")
            coll_list_recyclerview.adapter = collectionsAdapter
            coll_list_recyclerview.layoutManager = LinearLayoutManager(this)

        }



    }
}