package com.example.movies.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.BottomNavigationActivities.MoreActivities.CollectionMoviesList
import com.example.movies.MovieDescription
import com.example.movies.R
import com.example.movies.RoomDB.CollectionData
import com.example.movies.RoomDB.UserDataBase
import kotlinx.android.synthetic.main.activity_movie_description.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CollectionsAdapter(
    val context: Context,
    val data: List<CollectionData>,
    val checkactivity: String,
    val id: Int,
    val title: String?,
    val image: String?,
    val overview: String?,
    val rating: Float,
    val check: Int,
    val relase_date: String?
):
    RecyclerView.Adapter<CollectionsAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        if (checkactivity == "getCollectionData"){
            var view = LayoutInflater.from(context).inflate(R.layout.movie_paging_layout, parent, false)
            return MyHolder(view)
        }else{
            var view = LayoutInflater.from(context).inflate(R.layout.collection_list, parent, false)
            return MyHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var getdata = data[position]
        if (checkactivity == "insertion"){
            holder.collect.text = getdata.collection_name
            holder.collect.setOnClickListener {
                var checkexistdata: Boolean = UserDataBase.getInstance(context).userDao().checkExistingCollection(id)
                if (checkexistdata == true){
                }else{
                    var mov_data = CollectionData(id, getdata.collection_name, title, image, overview, rating, check, relase_date)
                    GlobalScope.launch {
                        UserDataBase.getInstance(context).userDao().collectionInsertion(mov_data)
                    }
                }
                (context as Activity).recreate()
            }

        }else if (checkactivity == "ActivityOnClick"){
            holder.collect.text = getdata.collection_name
            holder.collect.setOnClickListener {
                val intent = Intent(context, CollectionMoviesList::class.java)
                intent.putExtra("CollectionName",getdata.collection_name)
                context.startActivity(intent)
            }

        }else if (checkactivity == "getCollectionData"){
            Glide.with(context).load(getdata.movie_image).into(holder.img)
            holder.title.text = getdata.movie_title

            //Remove From Clooections
            holder.close.setOnClickListener {
                GlobalScope.launch {
                    UserDataBase.getInstance(context).userDao().deleteCollectionMovie(getdata.movie_id!!)
                }
                Toast.makeText(context, "Close Delete Sucessfully...", Toast.LENGTH_SHORT).show()
                (context as Activity).recreate()
            }

            holder.layout.setOnClickListener {

                val intent = Intent(context, MovieDescription::class.java)
                intent.putExtra("movie_img", getdata.movie_image)
                intent.putExtra("movie_title", getdata.movie_title)
                val rating : Float? = getdata.movie_rating
                intent.putExtra("rating", rating)
                intent.putExtra("check", getdata.check_cat)
                intent.putExtra("DATE", getdata.relase_date)
                intent.putExtra("movie_id", getdata.movie_id)
                intent.putExtra("movie_overview", getdata.movie_overview)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var collect = itemView.findViewById<TextView>(R.id.collectiontext)

        var img = itemView.findViewById<ImageView>(R.id.movie_img)
        var title = itemView.findViewById<TextView>(R.id.moviename)
        var close = itemView.findViewById<ImageView>(R.id.remove_fav)
        var layout = itemView.findViewById<RelativeLayout>(R.id.paging_click_cover)
    }
}