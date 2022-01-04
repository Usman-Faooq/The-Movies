package com.example.movies.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Watch_List")
data class WatchListData(

    @PrimaryKey(autoGenerate = true)
    var movie_id: Int?,
    var trailer_id: String?,
    var movie_name: String?,
    var trailer_title: String?,
    var check_cat: Int?,
    var trailerimage: String?


    )