package com.example.movies.BottomNavigationActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.example.movies.Movies_Search
import com.example.movies.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomnavigation
import kotlinx.android.synthetic.main.activity_search.*

class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        bottomnavigation.selectedItemId = R.id.search_act
        bottomnavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_act ->{
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.search_act ->{
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.activity_act ->{
                    val intent = Intent(this, FavActivity::class.java);
                    overridePendingTransition(0,0)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            true
        }

        search_data.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH){
                var intent = Intent(this, Movies_Search::class.java)
                intent.putExtra("serach_input", search_data.text.toString())
                startActivity(intent)
                true
            }
            false
        }

        category1.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "action")
            startActivity(intent)
        }

        category2.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "adventure")
            startActivity(intent)
        }

        category3.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "animation")
            startActivity(intent)
        }

        category4.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "comedy")
            startActivity(intent)
        }


        browes1.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "crime")
            startActivity(intent)
        }

        browes2.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "documentary")
            startActivity(intent)
        }

        browes3.setOnClickListener {
            var intent = Intent(this, Movies_Search::class.java)
            intent.putExtra("serach_input", "marvel")
            startActivity(intent)
        }
    }

}