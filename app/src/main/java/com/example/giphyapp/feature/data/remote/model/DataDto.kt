package com.example.giphyapp.feature.data.remote.model

import com.google.gson.annotations.SerializedName

data class DataDto<DataObject>(
    @SerializedName("data") val result: List<DataObject>
)

data class DataObject(
    @SerializedName("images") val images: DataImage
)

data class DataImage(
    @SerializedName("original") val originalImage: OriginalImage
)

data class OriginalImage(
    val url: String,
    val width: String
)