package com.example.photoshow.di

import android.app.Application
import androidx.room.Room
import com.example.photoshow.apptestshared.FakePhotoRemoteService
import com.example.photoshow.apptestshared.buildRemotePhotos
import com.example.photoshow.data.database.PhotoDao
import com.example.photoshow.data.database.PhotoShowDatabase
import com.example.photoshow.data.server.PhotoRemoteService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        PhotoShowDatabase::class.java
    ).build()

    @Provides
    @Singleton
    fun provideTableDao(db: PhotoShowDatabase): PhotoDao = db.photoDao()

    @Provides
    @Reusable
    fun providePhotoRemoteService(): PhotoRemoteService =
        FakePhotoRemoteService(
            buildRemotePhotos(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
            )
        )
}
