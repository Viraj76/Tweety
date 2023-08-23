package com.example.tweetsy.repository

import com.example.tweetsy.api.TwitsyApi
import com.example.tweetsy.model.Tweets
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsRepository @Inject constructor(private val twitsyApi: TwitsyApi) {

    /*
        Here we are keeping our data in the mutableStateflow and we are making it as
        private so that the authority to change the data in the _tweets will be to this
        class , we dont want to expose it.
        further for accessing from other classes we are keeping tweets in the state flow
     */

    private val _tweets = MutableStateFlow<List<Tweets>>(emptyList())
    val tweets : StateFlow<List<Tweets>>
        get() = _tweets

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories : StateFlow<List<String>>
        get() = _categories

    suspend fun getTweets(category : String){
        val result = twitsyApi.getTweets(category)
        if(result.isSuccessful && result.body() != null){
            _tweets.emit(result.body()!!)
        }
    }

    suspend fun getCategories(){
        val result = twitsyApi.getCategory()
        if(result.isSuccessful && result.body() != null){
            _categories.emit(result.body()!!)
        }
    }
}