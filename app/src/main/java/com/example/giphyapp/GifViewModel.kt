package com.example.giphyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifViewModel @Inject constructor(
    private val gifRepository: GifRepository
) : ViewModel() {
    fun getGifs(): Flow<PagingData<DataObject>> {
        return gifRepository.getGifs().cachedIn(viewModelScope)
    }
}