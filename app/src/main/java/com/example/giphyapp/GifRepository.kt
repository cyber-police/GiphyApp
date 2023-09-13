package com.example.giphyapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val gifService: GifService
) {
    fun getGifs(): Flow<PagingData<DataObject>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifsPagingSource(gifService)
            }
        ).flow
    }
}