package com.example.giphyapp

import retrofit2.Response
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val gifService: GifService
) {
    suspend fun getGifs(): Response<DataDto> {
        return gifService.getGifs()
    }
}