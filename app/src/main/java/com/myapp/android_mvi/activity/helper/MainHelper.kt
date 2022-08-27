package com.myapp.android_mvi.activity.helper

import com.myapp.android_mvi.model.Post

interface MainHelper {
    suspend fun allPosts(): ArrayList<Post>
    suspend fun deletePost(id: Int): Post
}
