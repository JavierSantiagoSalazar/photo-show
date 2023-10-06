package com.example.photoshow.di

import android.app.Application
import androidx.room.Room
import com.example.photoshow.data.database.PhotoDao
import com.example.photoshow.data.database.PhotoShowDatabase
import com.example.photoshow.data.server.PhotoRemoteService
import com.example.photoshow.di.annotations.ApiUrl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        PhotoShowDatabase::class.java,
        "photo-show-db"
    ).build()

    @Provides
    @Singleton
    fun provideDao(db: PhotoShowDatabase): PhotoDao = db.photoDao()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String =
        "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Reusable
    fun providePhotoService(retrofit: Retrofit): PhotoRemoteService {
        return retrofit.create(PhotoRemoteService::class.java)
    }

}
