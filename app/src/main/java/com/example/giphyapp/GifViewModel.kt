package com.example.giphyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifViewModel @Inject constructor(
    private val gifRepository: GifRepository
) : ViewModel() {

    private val _dataResult = MutableLiveData<DataResult>()
    val dataResult: LiveData<DataResult> = _dataResult

    fun getGifs() {
        viewModelScope.launch {
            try {
                val response = gifRepository.getGifs()
                if (response.isSuccessful) {
                    val data = response.body()
                    _dataResult.postValue(DataResult.Success(data!!))
                } else {
                    _dataResult.postValue(DataResult.Error("Something went wrong..."))
                }
            } catch (e: Exception) {
                _dataResult.postValue(DataResult.Error("Something went wrong..."))
            }
        }
    }
}