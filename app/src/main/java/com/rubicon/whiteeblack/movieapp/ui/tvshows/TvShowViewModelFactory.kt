package com.rubicon.whiteeblack.movieapp.ui.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository


class TvShowViewModelFactory(
    private val repository: TvShowRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = TvShowViewModel(
        repository
    ) as T
}