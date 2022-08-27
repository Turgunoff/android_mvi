package com.myapp.android_mvi.activity.helper

import com.myapp.android_mvi.model.Post
import com.myapp.android_mvi.network.service.PostService

class MainHelperImpl(private val postService: PostService) : MainHelper {

    override suspend fun allPosts(): ArrayList<Post> {
        return postService.allPosts()
    }

    override suspend fun deletePost(id: Int): Post {
        return postService.deletePost(id)
    }
}