package com.rubicon.whiteeblack.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class DetailViewModel(private var title : String, private var imageUrl : String, private var description : String ) : ViewModel() {
    data class MovieDetails(var title: String, var imageUrl: String, var description: String)
    private val liveData : MutableLiveData<MovieDetails> = MutableLiveData<MovieDetails>()
    init {
        liveData.value = MovieDetails(title,imageUrl,description)
    }
    fun getMovie() : LiveData<MovieDetails>
    {
        return liveData
    }
}
