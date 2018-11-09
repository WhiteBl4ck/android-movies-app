package com.rubicon.whiteeblack.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import com.rubicon.whiteeblack.movieapp.network.ApiService
import com.rubicon.whiteeblack.movieapp.utils.API_KEY
import com.rubicon.whiteeblack.movieapp.vo.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository private constructor(private val apiService: ApiService) {

    private val liveDataSearchTvShows = MutableLiveData<TopRatedTvShowsResponse>()
    val liveDataTopRatedTvShows = MutableLiveData<Resource<TopRatedTvShowsResponse>>()

    // simple cache to avoid network bandwidth loss
    var cacheTopRatedMoviesResponse : TopRatedTvShowsResponse? = null

    fun getTopRatedTvShows() : LiveData<Resource<TopRatedTvShowsResponse>>
    {
        if (cacheTopRatedMoviesResponse != null)
            liveDataTopRatedTvShows.value = Resource.success(cacheTopRatedMoviesResponse)
        else fetchTvShows()
        return liveDataTopRatedTvShows
    }

    private fun fetchTvShows() {
        liveDataTopRatedTvShows.value = Resource.loading(liveDataTopRatedTvShows.value?.data)
        apiService.getTopRatedTvShows(API_KEY).enqueue(object : Callback<TopRatedTvShowsResponse> {
            override fun onFailure(call: Call<TopRatedTvShowsResponse>, t: Throwable) {
                liveDataTopRatedTvShows.value = Resource.error(t.message.toString(), null)
            }

            override fun onResponse(call: Call<TopRatedTvShowsResponse>, response: Response<TopRatedTvShowsResponse>) {
                liveDataTopRatedTvShows.value = Resource.success(response.body())
                cacheTopRatedMoviesResponse = response.body()
            }
        })
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

    fun loadTopRatedMovies() {
        fetchTvShows()
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