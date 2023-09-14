package com.example.giphyapp.feature.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphyapp.BuildConfig
import com.example.giphyapp.feature.data.remote.api.GifService
import com.example.giphyapp.feature.data.remote.model.DataObject
import retrofit2.HttpException
import java.io.IOException

class GifsPagingSource(
    private val gifService: GifService
) : PagingSource<Int, DataObject>() {

    companion object {
        const val API_KEY = BuildConfig.GIPHY_API_KEY
        const val PAGE_SIZE = BuildConfig.PAGE_SIZE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataObject> {
        val pageIndex = params.key ?: 0
        return try {
            val response = gifService.getGifs(
                apiKey = API_KEY,
                offset = pageIndex
            )
            val gifs = response.result
            val nextKey =
                if (gifs.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / PAGE_SIZE)
                }
            LoadResult.Page(
                data = gifs,
                prevKey = if (pageIndex == 0) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}