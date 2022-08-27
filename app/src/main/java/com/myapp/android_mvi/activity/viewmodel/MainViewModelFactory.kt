package com.myapp.android_mvi.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.android_mvi.activity.helper.MainHelper
import com.myapp.android_mvi.repository.PostRepository

class MainViewModelFactory(private val mainHelper: MainHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(PostRepository(mainHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}