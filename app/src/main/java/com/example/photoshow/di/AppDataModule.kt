package com.example.photoshow.di

import com.example.data.datasource.PhotoLocalDataSource
import com.example.data.datasource.PhotoRemoteDataSource
import com.example.photoshow.data.database.PhotoLocalDataSourceImpl
import com.example.photoshow.data.server.PhotoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindPhotoRemoteDataSource(photoRemoteDataSourceImpl: PhotoRemoteDataSourceImpl): PhotoRemoteDataSource

    @Binds
    abstract fun bindPhotoLocalDataSource(photoLocalDataSource: PhotoLocalDataSourceImpl): PhotoLocalDataSource

}
