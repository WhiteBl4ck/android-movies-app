package com.rubicon.whiteeblack.movieapp.network

import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String): Call<TopRatedMoviesResponse>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(@Query("api_key") apiKey: String): Call<TopRatedTvShowsResponse>

    @GET("search/tv")
    fun searchTvShows(@Query("api_key") apiKey: String,@Query("query") query: String): Call<TopRatedTvShowsResponse>

    @GET("search/movie")
    fun searchMovies(@Query("api_key") apiKey: String,@Query("query") query: String): Call<TopRatedMoviesResponse>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}