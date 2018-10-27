package com.rubicon.whiteeblack.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository

class MoviesViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MoviesViewModel(
        repository
    ) as T
}