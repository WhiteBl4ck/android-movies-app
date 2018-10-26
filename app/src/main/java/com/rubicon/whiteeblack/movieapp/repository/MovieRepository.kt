package com.rubicon.whiteeblack.movieapp.repository

import androidx.lifecycle.LiveData
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse
import com.rubicon.whiteeblack.movieapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import com.rubicon.whiteeblack.movieapp.utils.API_KEY


class MovieRepository private constructor(private val apiService: ApiService) {

    fun getTopRatedMovies() : LiveData<TopRatedMoviesResponse>
    {
        val data = MutableLiveData<TopRatedMoviesResponse>()
        apiService.getTopRated(API_KEY).enqueue(object : Callback<TopRatedMoviesResponse>{
            override fun onFailure(call: Call<TopRatedMoviesResponse>, t: Throwable) {
                // TODO handle failure and status
            }
            override fun onResponse(call: Call<TopRatedMoviesResponse>, response: Response<TopRatedMoviesResponse>) {
                data.value = response.body()
            }

        })
        return data
    }
    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null
        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(apiService).also { instance = it }
            }
    }
}