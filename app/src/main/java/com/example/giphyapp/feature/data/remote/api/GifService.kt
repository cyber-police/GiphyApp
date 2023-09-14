package com.example.giphyapp.feature.data.remote.api

import com.example.giphyapp.feature.data.remote.model.DataDto
import com.example.giphyapp.feature.data.remote.model.DataObject
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {
    @GET("v1/gifs/trending")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("offset") offset: Int
    ): DataDto<DataObject>
}