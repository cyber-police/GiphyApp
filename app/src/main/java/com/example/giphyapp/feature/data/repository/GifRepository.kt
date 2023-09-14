package com.example.giphyapp.feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.giphyapp.BuildConfig
import com.example.giphyapp.feature.data.remote.model.DataObject
import com.example.giphyapp.feature.data.remote.api.GifService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val gifService: GifService
) {
    fun getGifs(): Flow<PagingData<DataObject>> {
        return Pager(
            config = PagingConfig(
                pageSize = BuildConfig.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifsPagingSource(gifService)
            }
        ).flow
    }
}