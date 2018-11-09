package com.rubicon.whiteeblack.movieapp.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.model.TopRatedTvShowsResponse
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository
import com.rubicon.whiteeblack.movieapp.vo.Resource

class TvShowViewModel (private val repository: TvShowRepository) : ViewModel(){
    fun topRatedTvShows() : LiveData<Resource<TopRatedTvShowsResponse>> = repository.getTopRatedTvShows()
    fun loadTopRatedTvShows() {
        repository.loadTopRatedMovies()
    }
}