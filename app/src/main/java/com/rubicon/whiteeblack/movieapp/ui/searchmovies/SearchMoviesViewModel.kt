package com.rubicon.whiteeblack.movieapp.ui.searchmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository


class SearchMoviesViewModel (private val repository: MovieRepository) : ViewModel(){
    val query = MutableLiveData<String>()
    fun searchMovies(query : String) : LiveData<TopRatedMoviesResponse> = repository.searchMovies(query)
}
