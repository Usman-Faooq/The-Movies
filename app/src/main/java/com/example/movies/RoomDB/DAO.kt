package com.example.movies.RoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    @Insert()
    fun insertData(data: Data)

    @Insert()
    fun insertWatchData(watchListData: WatchListData)
    @Query("SELECT EXISTS(SELECT * FROM Watch_List WHERE movie_id = :movie_id AND trailer_id = :trailer_id)")
    fun checkWatchListExisting(movie_id: Int, trailer_id: String) : Boolean
    @Query("SELECT * FROM Watch_List")
    fun displayWatchList() : List<WatchListData>



    @Query("SELECT EXISTS(SELECT * FROM MoviesData WHERE movie_id = :movieid)")
    fun checkExistingData(movieid : Int) : Boolean

    @Query("SELECT * FROM MoviesData")
    fun displayData(): List<Data>

    @Query("DELETE FROM MoviesData WHERE movie_id = :movieid")
    fun deleteData(movieid: Int)


}