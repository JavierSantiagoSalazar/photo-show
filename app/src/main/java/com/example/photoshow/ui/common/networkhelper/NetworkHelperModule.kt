package com.example.photoshow.ui.common.networkhelper

import com.example.photoshow.ui.common.networkhelper.NetworkHelper
import com.example.photoshow.ui.common.networkhelper.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkHelperModule {

    @Provides
    @Reusable
    fun provideNetworkHelper(networkHelperImpl: NetworkHelperImpl): NetworkHelper = networkHelperImpl
}
