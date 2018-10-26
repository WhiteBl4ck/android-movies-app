package com.rubicon.whiteeblack.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository

class TvShowViewModel (private val repository: TvShowRepository) : ViewModel(){
    val topRatedTvShows : LiveData<TopRatedTvShowsResponse> = repository.getTopRatedTvShows()
}