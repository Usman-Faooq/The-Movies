package com.example.movies.RoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO {

    //WatchList
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWatchData(watchListData: WatchListData)
    @Query("SELECT EXISTS(SELECT * FROM Watch_List WHERE movie_id = :movie_id AND trailer_id = :trailer_id)")
    fun checkWatchListExisting(movie_id: Int, trailer_id: String) : Boolean
    @Query("SELECT * FROM Watch_List")
    fun displayWatchList() : List<WatchListData>
    @Query("DELETE FROM Watch_List WHERE movie_id=:movieid")
    fun deleteWatchList(movieid: Int)


    //Favourites
    @Insert()
    fun insertData(data: Data)
    @Query("SELECT EXISTS(SELECT * FROM MoviesData WHERE movie_id = :movieid)")
    fun checkExistingData(movieid : Int) : Boolean
    @Query("SELECT * FROM MoviesData")
    fun displayData(): List<Data>
    @Query("DELETE FROM MoviesData WHERE movie_id = :movieid")
    fun deleteData(movieid: Int)


    //Collections
    @Insert()
    fun collectionInsertion(collectionData: CollectionData)
    @Query("SELECT EXISTS(SELECT * FROM CollecrionTable WHERE movie_id = :movieid)")
    fun checkExistingCollection(movieid : Int) : Boolean
    @Query("SELECT DISTINCT collection_name FROM CollecrionTable")
    fun displayCollections(): List<CollectionData>
    @Query("SELECT * FROM collecriontable WHERE collection_name = :collectionType")
    fun displayAll(collectionType : String) : List<CollectionData>
    @Query("DELETE FROM CollecrionTable WHERE movie_id = :movieid")
    fun deleteCollectionMovie(movieid: Int)


}