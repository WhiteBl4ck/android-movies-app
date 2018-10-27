package com.rubicon.whiteeblack.movieapp.ui.searchtvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository

class SearchTvShowsViewModel (private val repository: TvShowRepository) : ViewModel(){

    val query = MutableLiveData<String>()
    fun searchTvShows(query : String) : LiveData<TopRatedTvShowsResponse> = repository.searchTvShows(query)
}
