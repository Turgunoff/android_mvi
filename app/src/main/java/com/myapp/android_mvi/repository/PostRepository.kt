package com.myapp.android_mvi.repository

import com.myapp.android_mvi.activity.helper.MainHelper

class PostRepository(private val mainHelper: MainHelper) {

    suspend fun allPosts() = mainHelper.allPosts()
    suspend fun deletePost(id: Int) = mainHelper.deletePost(id)

}