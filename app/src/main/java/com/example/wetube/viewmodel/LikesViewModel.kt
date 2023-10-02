package com.example.wetube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LikesViewModel : ViewModel() {
    private val likedVideos = mutableSetOf<String>()
    private val _likedVideosLiveData = MutableLiveData<Set<String>>()
    val likedVideosLiveData : LiveData<Set<String>> get() = _likedVideosLiveData

    fun isVideoLiked(videoId: String): Boolean {
        return likedVideos.contains(videoId)
    }

    fun toggleLike(videoId: String) {
        if (likedVideos.contains(videoId)) {
            likedVideos.remove(videoId)
        } else {
            likedVideos.add(videoId)
        }
        _likedVideosLiveData.value = likedVideos
    }
}