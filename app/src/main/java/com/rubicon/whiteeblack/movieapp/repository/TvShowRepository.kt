package com.rubicon.whiteeblack.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import com.rubicon.whiteeblack.movieapp.network.ApiService
import com.rubicon.whiteeblack.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository private constructor(private val apiService: ApiService) {

    private val liveDataSearchTvShows = MutableLiveData<TopRatedTvShowsResponse>()
    val liveDataTopRatedTvShows = MutableLiveData<TopRatedTvShowsResponse>()

    fun getTopRatedTvShows() : LiveData<TopRatedTvShowsResponse>
    {
        apiService.getTopRatedTvShows(API_KEY).enqueue(object : Callback<TopRatedTvShowsResponse> {
            override fun onFailure(call: Call<TopRatedTvShowsResponse>, t: Throwable) {
                //TODO handle onFailure
            }
            override fun onResponse(call: Call<TopRatedTvShowsResponse>, response: Response<TopRatedTvShowsResponse>) {
                liveDataTopRatedTvShows.value = response.body()
            }
        })
        return liveDataTopRatedTvShows
    }
    fun searchTvShows(query : String) : LiveData<TopRatedTvShowsResponse>
    {
        apiService.searchTvShows(API_KEY,query).enqueue(object : Callback<TopRatedTvShowsResponse> {
            override fun onFailure(call: Call<TopRatedTvShowsResponse>, t: Throwable) {
                //TODO handle onFailure
            }
            override fun onResponse(call: Call<TopRatedTvShowsResponse>, response: Response<TopRatedTvShowsResponse>) {
                liveDataSearchTvShows.value = response.body()
            }
        })
        return liveDataSearchTvShows
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: TvShowRepository? = null
        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: TvShowRepository(apiService).also { instance = it }
            }
    }
}