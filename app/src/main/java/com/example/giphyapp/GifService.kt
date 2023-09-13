package com.example.giphyapp

import retrofit2.Response
import retrofit2.http.GET

interface GifService {
    @GET("gifs/trending?api_key=5uWKGke21c6B4Z7YnXWhfiIcRY3oqnko")
    suspend fun getGifs(): Response<DataDto>
}