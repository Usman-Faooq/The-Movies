package com.example.movies.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class, WatchListData::class, CollectionData::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao():DAO
    companion object{
        var INSTANCE : UserDataBase? = null
        fun getInstance(context: Context) : UserDataBase{

            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "Favourite_Movies").fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}