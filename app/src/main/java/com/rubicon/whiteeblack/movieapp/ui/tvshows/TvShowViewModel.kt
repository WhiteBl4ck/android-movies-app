package com.rubicon.whiteeblack.movieapp.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository

class TvShowViewModel (private val repository: TvShowRepository) : ViewModel(){
    fun topRatedTvShows() : LiveData<TopRatedTvShowsResponse> = repository.getTopRatedTvShows()
}