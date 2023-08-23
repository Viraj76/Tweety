package com.example.tweetsy.api

import com.example.tweetsy.model.Tweets
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


/*
    -> HERE we declare functions for our end points  , so here we will call two endpoints i.e. there will be two functions.
    body will be provided by the retrofit

    -> Headers for the static call and Header for the dynamic call i.e. we will pass the value

    -> Here we GET the end points. Response will depend what is the data we are getting from the API
 */
interface TwitsyApi {


    @GET("/v3/b/64e637048e4aa6225ed4466b?meta=false")
   suspend fun getTweets(@Header("X-JSON-Path") categories : String) : Response<List<Tweets>>

    @GET("/v3/b/64e637048e4aa6225ed4466b?meta=false")
    @Headers("X-JSON-Path : tweets.category")
    suspend fun getCategory() : Response<List<String>>

}