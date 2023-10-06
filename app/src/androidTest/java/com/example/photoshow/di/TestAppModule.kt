package com.example.interrapidismotest.di

import android.app.Application
import androidx.room.Room
import com.example.interrapidismotest.apptestshared.FakeTableRemoteService
import com.example.interrapidismotest.apptestshared.buildRemoteTables
import com.example.interrapidismotest.data.database.TableDao
import com.example.interrapidismotest.data.database.TableDatabase
import com.example.interrapidismotest.data.server.TableRemoteService
import com.example.interrapidismotest.di.annotations.User
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
    @User
    fun provideUser(): String = "admin"

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        TableDatabase::class.java
    ).build()

    @Provides
    @Singleton
    fun provideTableDao(db: TableDatabase): TableDao = db.tableDao()

    @Provides
    @Reusable
    fun provideTableRemoteService(): TableRemoteService =
        FakeTableRemoteService(
            buildRemoteTables(
                "TableName1",
                "TableName2",
                "TableName3",
                "TableName4",
                "TableName5",
                "TableName6",
                "TableName7",
                "TableName8",
                "TableName9",
                "TableName10",
                "TableName11",
                "TableName12"
            )
        )
}