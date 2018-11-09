package com.rubicon.whiteeblack.movieapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository
import com.rubicon.whiteeblack.movieapp.model.TopRatedMoviesResponse
import com.rubicon.whiteeblack.movieapp.vo.Resource

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    fun topRatedMoviesResponse() : LiveData<Resource<TopRatedMoviesResponse>> = repository.getTopRatedMovies()
    fun loadTopRatedMovies() {
        repository.loadTopRatedMovies()
    }
}
