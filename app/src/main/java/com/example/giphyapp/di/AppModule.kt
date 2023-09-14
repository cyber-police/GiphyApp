package com.example.giphyapp.di

import com.example.giphyapp.feature.data.repository.GifRepository
import com.example.giphyapp.feature.data.remote.api.GifService
import com.example.giphyapp.feature.presentation.ui.GifViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    @Provides
    fun provideGifService(): GifService {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GifService::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideGifViewModelFactory(
        gifRepository: GifRepository
    ): GifViewModelFactory {
        return GifViewModelFactory(gifRepository)
    }

    @Provides
    fun provideRepository(
        gifService: GifService
    ): GifRepository {
        return GifRepository(gifService)
    }
}