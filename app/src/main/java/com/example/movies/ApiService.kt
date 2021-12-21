package com.example.movies

import com.example.movies.DataClasses.CastLink
import com.example.movies.DataClasses.LinkData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.themoviedb.org/"
interface Apiinterface {

    @GET("3/movie/{movie_id}/credits?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getMovieCast(@Path("movie_id") id: Int) : Call<CastLink>

    @GET("3/tv/{movie_id}/credits?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getTVCast(@Path("movie_id") id: Int) : Call<CastLink>

    @GET("3/movie/now_playing?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getInTheaterMovies() : Call<LinkData>

    @GET("3/movie/popular?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getPopularMovies() : Call<LinkData>

    @GET("3/movie/upcoming?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getUpcomingMovies(): Call<LinkData>

    @GET("3/movie/top_rated?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun gettopRatedMovies(): Call<LinkData>

    @GET("3/tv/top_rated?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getTopRatedTVShow(): Call<LinkData>

    @GET("3/tv/popular?api_key=0b879c1b4ba756a622fb4980d2d446f4")
    fun getPopularTVShow(): Call<LinkData>
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