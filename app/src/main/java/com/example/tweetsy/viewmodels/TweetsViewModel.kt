package com.example.tweetsy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.model.Tweets
import com.example.tweetsy.repository.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(private val repository: TweetsRepository) : ViewModel() {
    val tweets : StateFlow<List<Tweets>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            repository.getTweets("Android")
        }
    }
}