package com.example.giphyapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class GifsPagingSource(
    private val gifService: GifService
) : PagingSource<Int, DataObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataObject> {
        val pageIndex = params.key ?: 1
        return try {
            val response = gifService.getGifs(
                apiKey = "5uWKGke21c6B4Z7YnXWhfiIcRY3oqnko",
                offset = pageIndex
            )
            val gifs = response.result
            val nextKey =
                if (gifs.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / 50)
                }
            LoadResult.Page(
                data = gifs,
                prevKey = if (pageIndex == 1) null else pageIndex,
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