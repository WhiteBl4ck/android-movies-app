package com.rubicon.whiteeblack.movieapp.utils

import com.rubicon.whiteeblack.movieapp.network.ApiService
import com.rubicon.whiteeblack.movieapp.repository.MovieRepository
import com.rubicon.whiteeblack.movieapp.repository.TvShowRepository
import com.rubicon.whiteeblack.movieapp.ui.searchtvshows.SearchTvShowsViewModelFactory
import com.rubicon.whiteeblack.movieapp.ui.detail.DetailViewModelFactory
import com.rubicon.whiteeblack.movieapp.ui.movies.MoviesViewModelFactory
import com.rubicon.whiteeblack.movieapp.ui.searchmovies.SearchMoviesViewModelFactory
import com.rubicon.whiteeblack.movieapp.ui.tvshows.TvShowViewModelFactory

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
    fun provideSearchTvShowsViewModelFactory() : SearchTvShowsViewModelFactory
    {
        return SearchTvShowsViewModelFactory(getTvShowRepository())
    }

    fun provideSearchMoviewViewModelFactory() : SearchMoviesViewModelFactory
    {
        return SearchMoviesViewModelFactory(getMovieRepository())
    }
    fun provideDetailViewModelFactory(title : String,imageUrl : String,description : String) : DetailViewModelFactory
    {
        return DetailViewModelFactory(title, imageUrl, description)
    }
}