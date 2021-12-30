package com.example.movies

import com.example.movies.DataClasses.CastLink
import com.example.movies.DataClasses.LinkData
import com.example.movies.DataClasses.MovieDetail
import com.example.movies.DataClasses.TrailerLink
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/"
interface Apiinterface {

    //Get Movie Cast...
    @GET("3/movie/{movie_id}/credits?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getMovieCast(@Path("movie_id") id: Int) : Call<CastLink>

    //Get TV Cast...
    @GET("3/tv/{movie_id}/credits?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getTVCast(@Path("movie_id") id: Int) : Call<CastLink>

    //---------------------------------------------------------------------------

    @GET("3/movie/now_playing?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getInTheaterMovies(@Query("page") p : Int) : Call<LinkData>

    @GET("3/movie/popular?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getPopularMovies(@Query("page") p : Int) : Call<LinkData>

    @GET("3/movie/upcoming?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getUpcomingMovies(@Query("page") p : Int): Call<LinkData>

    @GET("3/movie/top_rated?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun gettopRatedMovies(@Query("page") p : Int): Call<LinkData>

    @GET("3/tv/top_rated?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getTopRatedTVShow(@Query("page") p : Int): Call<LinkData>

    @GET("3/tv/popular?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getPopularTVShow(@Query("page") p : Int): Call<LinkData>

    //---------------------------------------------------------------------

    //Movies Searching...
    @GET("3/search/movie?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getSearchMovies(@Query("query") title : String) : Call<LinkData>

    //TV Show Searching...
    @GET("3/search/tv?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getSearchTV(@Query("query") name : String) : Call<LinkData>

    //---------------------------------------------------------------------

    //Get Movie Detail...
    @GET("https://api.themoviedb.org/3/movie/{movie_id}?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getMovieDetai(@Path("movie_id") id: Int) : Call<MovieDetail>

    //Get Movie Trailer
    @GET("3/movie/{movie_id}/videos?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getMovieTrailer(@Path("movie_id") id: Int) : Call<TrailerLink>

}

object ApiService{
    val instance : Apiinterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(Apiinterface::class.java)
    }
}