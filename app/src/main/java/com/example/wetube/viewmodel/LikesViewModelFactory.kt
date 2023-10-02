package com.example.wetube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class LikesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LikesViewModel::class.java)) {
            return LikesViewModel() as T
        }
        throw IllegalArgumentException("View Model Class not found")
    }
}