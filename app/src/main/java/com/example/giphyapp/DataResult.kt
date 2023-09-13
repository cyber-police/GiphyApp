package com.example.giphyapp

sealed class DataResult {
    data class Success(val data: DataDto) : DataResult()
    data class Loading(val state: Boolean) : DataResult()
    data class Error(val errorMessage: String) : DataResult()
}