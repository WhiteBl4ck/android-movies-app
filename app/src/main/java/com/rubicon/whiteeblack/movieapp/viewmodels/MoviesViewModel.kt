package com.rubicon.whiteeblack.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    val topRatedMoviesResponse : LiveData<TopRatedMoviesResponse> = repository.getTopRatedMovies()
}
