package com.example.giphyapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class AppModule {
    @Provides
    fun provideGifService(): GifService {
        return Retrofit.Builder()
            .baseUrl("api.giphy.com/v1/gifs/trending")
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