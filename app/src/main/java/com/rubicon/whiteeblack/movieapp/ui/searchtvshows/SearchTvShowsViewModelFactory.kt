package com.rubicon.whiteeblack.movieapp.ui.searchtvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository



class SearchTvShowsViewModelFactory(
    private val repository: TvShowRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = SearchTvShowsViewModel(
        repository
    ) as T
}