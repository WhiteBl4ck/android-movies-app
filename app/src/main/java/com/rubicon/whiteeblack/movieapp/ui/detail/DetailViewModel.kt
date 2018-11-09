package com.rubicon.whiteeblack.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel(title : String, imageUrl : String, description : String ) : ViewModel() {
    data class MovieDetails(var title: String, var imageUrl: String, var description: String)
    private val liveData : MutableLiveData<MovieDetails> = MutableLiveData()
    init {
        liveData.value =
                MovieDetails(title, imageUrl, description)
    }
    fun getMovie() : LiveData<MovieDetails>
    {
        return liveData
    }
}
