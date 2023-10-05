package com.example.wetube.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wetube.LikedVideoPreferences
import com.example.wetube.model.NewList

class LikesViewModel(application: Application) : AndroidViewModel(application) {

    private val likedVideoPreferences = LikedVideoPreferences(application)

    private val _likedVideosLiveData = MutableLiveData<List<NewList>>()
    val likedVideosLiveData: LiveData<List<NewList>> get() = _likedVideosLiveData

    private val _currentLikedState = MutableLiveData<Boolean>()
    val currentLikedState: LiveData<Boolean> get() = _currentLikedState

    init { refreshLikedVideos()}

    fun setVideoLiked(thumbnail: String, newList: NewList) {
        likedVideoPreferences.setVideoLiked(thumbnail, newList)
        refreshLikedVideos()
    }
    fun removeVideoLiked(thumbnail: String) {
        likedVideoPreferences.removeVideoLiked(thumbnail)
        refreshLikedVideos()
    }
    fun isVideoLiked(thumbnail: String): Boolean {
        return likedVideoPreferences.isVideoLiked(thumbnail)
    }
    fun toggleLike(thumbnail: String, newList: NewList) {
        if (isVideoLiked(thumbnail)) {
            removeVideoLiked(thumbnail)
            _currentLikedState.value = false
        } else {
            setVideoLiked(thumbnail, newList)
            _currentLikedState.value = true
        }
        refreshLikedVideos()
    }
    fun refreshLikedVideos() {
        _likedVideosLiveData.value = likedVideoPreferences.getLikedVideos()
    }
}