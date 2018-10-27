package com.rubicon.whiteeblack.movieapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    fun topRatedMoviesResponse() : LiveData<TopRatedMoviesResponse> = repository.getTopRatedMovies()
}
