package com.rubicon.whiteeblack.movieapp.utils

import com.rubicon.whiteeblack.movieapp.network.ApiService
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository
import com.rubicon.whiteeblack.movieapp.viewmodels.DetailViewModelFactory
import com.rubicon.whiteeblack.movieapp.viewmodels.MoviesViewModelFactory
import com.rubicon.whiteeblack.movieapp.viewmodels.TvShowViewModelFactory

object InjectorUtils {
    private fun getMovieRepository() : MovieRepository
    {
        return MovieRepository.getInstance(ApiService.create())
    }
    private fun getTvShowRepository() : TvShowRepository
    {
        return TvShowRepository.getInstance(ApiService.create())
    }

    fun provideMoviesViewModelFactory() : MoviesViewModelFactory
    {
        return MoviesViewModelFactory(getMovieRepository())
    }
    fun provideTvShowViewModelFactory() : TvShowViewModelFactory
    {
        return TvShowViewModelFactory(getTvShowRepository())
    }
    fun provideDetailViewModelFactory(title : String,imageUrl : String,description : String) : DetailViewModelFactory
    {
        return DetailViewModelFactory(title,imageUrl,description)
    }
}