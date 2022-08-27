package com.myapp.android_mvi.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.android_mvi.activity.intentstate.MainIntent
import com.myapp.android_mvi.activity.intentstate.MainState
import com.myapp.android_mvi.repository.PostRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.math.MathContext.UNLIMITED

class MainViewModel(private val repository: PostRepository) : ViewModel() {
    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Init)
    val state: StateFlow<MainState> get() = _state

    var postId: Int = 0

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.AllPosts -> apiAllPosts()
                    is MainIntent.DeletePost -> apiDeletePost()
                }
            }
        }
    }

    private fun apiAllPosts() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.AllPosts(repository.allPosts())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

    private fun apiDeletePost() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.DeletePost(repository.deletePost(postId))
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}