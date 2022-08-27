package com.myapp.android_mvi.activity.intentstate

sealed class MainIntent {
    object AllPosts : MainIntent()
    object DeletePost : MainIntent()
}