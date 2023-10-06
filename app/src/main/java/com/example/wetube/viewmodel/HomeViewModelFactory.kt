package com.example.wetube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.api.YoutubeApi
import com.example.wetube.repository.RepositoryHomeVideos

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val homeVideos: RepositoryHomeVideos) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeVideos) as T
        }
        throw IllegalArgumentException("View Model Class not found")
    }
}