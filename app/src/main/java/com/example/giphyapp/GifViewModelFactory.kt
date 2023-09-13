package com.example.giphyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class GifViewModelFactory @Inject constructor(
    private val gifRepository: GifRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GifRepository::class.java)
            .newInstance(gifRepository)
    }
}