package com.example.giphyapp.feature.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.giphyapp.feature.data.remote.model.DataObject
import com.example.giphyapp.feature.data.repository.GifRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifViewModel @Inject constructor(
    private val gifRepository: GifRepository
) : ViewModel() {
    fun getGifs(): Flow<PagingData<DataObject>> {
        return gifRepository.getGifs().cachedIn(viewModelScope)
    }
}