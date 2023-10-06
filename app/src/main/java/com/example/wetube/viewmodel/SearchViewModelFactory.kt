package com.example.wetube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.repository.RepositoryHomeVideos

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory (private val searchVideos: RepositoryHomeVideos) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchVideos) as T
        }
        throw IllegalArgumentException("View Model Class not found")
    }
}