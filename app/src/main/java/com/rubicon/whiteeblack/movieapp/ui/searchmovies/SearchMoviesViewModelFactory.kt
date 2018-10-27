package com.rubicon.whiteeblack.movieapp.ui.searchmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository
import com.rubicon.whiteeblack.movieapp.ui.movies.MoviesViewModel


class SearchMoviesViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = SearchMoviesViewModel(
        repository
    ) as T
}