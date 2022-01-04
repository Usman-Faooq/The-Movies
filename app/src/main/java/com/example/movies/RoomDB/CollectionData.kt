package com.example.movies.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CollecrionTable")
class CollectionData(
    @PrimaryKey(autoGenerate = true)
    var movie_id: Int?,
    var collection_name: String?,
    var movie_title: String?,
    var movie_image: String?,
    var movie_overview: String?,
    var movie_rating: Float?,
    var check_cat: Int?,
    var relase_date: String?
) {


}