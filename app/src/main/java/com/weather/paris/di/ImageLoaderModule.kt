package com.weather.paris.di

import android.content.Context
import com.bumptech.glide.RequestManager
import com.weather.paris.GlideApp
import com.weather.paris.GlideRequests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(ActivityComponent::class)
@Module
object ImageLoaderModule {

    @Provides
    fun provideGlideRequests(@ActivityContext context: Context): RequestManager = GlideApp.with(context)
}