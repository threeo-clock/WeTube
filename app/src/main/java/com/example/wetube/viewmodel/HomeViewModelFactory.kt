package com.example.wetube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.api.YoutubeApi

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val api :YoutubeApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(api) as T
        }
        throw IllegalArgumentException("View Model Class not found")
    }
}