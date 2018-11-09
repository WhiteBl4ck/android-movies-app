package com.rubicon.whiteeblack.movieapp.repository

import androidx.lifecycle.LiveData
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse
import com.rubicon.whiteeblack.movieapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import com.rubicon.whiteeblack.movieapp.utils.API_KEY
import com.rubicon.whiteeblack.movieapp.vo.Resource


class MovieRepository private constructor(private val apiService: ApiService) {

    //
    val liveDataMovies = MutableLiveData<Resource<TopRatedMoviesResponse>>()
    val liveDataSearchMovies = MutableLiveData<TopRatedMoviesResponse>()

    // simple cache to avoid network bandwidth loss
    var cacheTopRatedMoviesResponse : TopRatedMoviesResponse? = null

    fun getTopRatedMovies() : LiveData<Resource<TopRatedMoviesResponse>>
    {
        if (cacheTopRatedMoviesResponse != null)
            liveDataMovies.value = Resource.success(cacheTopRatedMoviesResponse)
        else fetchTopRatedMovies()
        return liveDataMovies
    }

    private fun fetchTopRatedMovies() {
        liveDataMovies.value = Resource.loading(liveDataMovies.value?.data)
        apiService.getTopRated(API_KEY).enqueue(object : Callback<TopRatedMoviesResponse> {
            override fun onFailure(call: Call<TopRatedMoviesResponse>, t: Throwable) {
                liveDataMovies.value = Resource.error(t.message.toString(),null)
            }

            override fun onResponse(call: Call<TopRatedMoviesResponse>, response: Response<TopRatedMoviesResponse>) {
                liveDataMovies.value = Resource.success(response.body())
                cacheTopRatedMoviesResponse = response.body()
            }

        })
    }

    fun searchMovies(query : String) : LiveData<TopRatedMoviesResponse>
    {
        apiService.searchMovies(API_KEY,query).enqueue(object : Callback<TopRatedMoviesResponse>{
            override fun onFailure(call: Call<TopRatedMoviesResponse>, t: Throwable) {

            }
            override fun onResponse(call: Call<TopRatedMoviesResponse>, response: Response<TopRatedMoviesResponse>) {
                liveDataSearchMovies.value = response.body()
            }

        })
        return liveDataSearchMovies
    }

    fun loadTopRatedMovies() {
        fetchTopRatedMovies()
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