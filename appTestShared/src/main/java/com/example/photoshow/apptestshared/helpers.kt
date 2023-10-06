package com.example.interrapidismotest.apptestshared

import com.example.interrapidismotest.data.database.TableLocalDataSourceImpl
import com.example.interrapidismotest.data.repository.TableRepository
import com.example.interrapidismotest.data.server.RemoteTable
import com.example.interrapidismotest.data.server.TableRemoteDataSourceImpl
import com.example.interrapidismotest.data.database.Table as DatabaseTable

fun buildTableRepositoryWith(
    localData: List<DatabaseTable>,
    remoteData: List<RemoteTable>,
): TableRepository {
    val localDataSource = TableLocalDataSourceImpl(FakeTableDao(localData))
    val remoteDataSource = TableRemoteDataSourceImpl("admin", FakeTableRemoteService(remoteData))
    return TableRepository(localDataSource, remoteDataSource)
}

fun buildDatabaseTables(vararg names: String) = names.map {
    DatabaseTable(
        tableName = it,
        primaryKey = "PrimaryKey",
        queryCreation = "",
        batchSize = 1,
        filter = "",
        error = "",
        fieldsNumber = 2,
        appMethod = "",
        dateUpdatesSync = ""
    )
}

fun buildRemoteTables(vararg name: String) = name.map {
    RemoteTable(
        tableName = it,
        primaryKey = "PrimaryKey $it",
        queryCreation = "",
        batchSize = 1,
        filter = "",
        error = "",
        fieldsNumber = 2,
        appMethod = "",
        dateUpdatesSync = ""
    )
}
