package com.ao.anew.di

import com.bumptech.glide.RequestManager
import com.ao.anew.ui.MainRecyclerAdapter
import com.ao.anew.util.TopSpacingItemDecoration

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsActivityModule {

    @NewsScope
    @Provides
    fun provideMainAdapter(requestManager: RequestManager) : MainRecyclerAdapter {
        return MainRecyclerAdapter(requestManager)
    }

    //Added in news scope only for testing
    @NewsScope
    @Provides
    fun provideItemDecoration() : TopSpacingItemDecoration {
        return TopSpacingItemDecoration(30)
    }

}