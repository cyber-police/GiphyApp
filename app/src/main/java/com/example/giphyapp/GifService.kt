package com.example.giphyapp

import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {
    @GET("v1/gifs/trending")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("offset") offset: Int
    ): DataDto<DataObject>
}