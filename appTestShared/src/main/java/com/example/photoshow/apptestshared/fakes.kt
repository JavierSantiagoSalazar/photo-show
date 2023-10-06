package com.example.interrapidismotest.apptestshared

import com.example.interrapidismotest.data.database.TableDao
import com.example.interrapidismotest.data.server.RemoteTable
import com.example.interrapidismotest.data.server.TableRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.interrapidismotest.data.database.Table as DatabaseTable

class FakeTableDao(tables: List<DatabaseTable> = emptyList()) : TableDao {

    private val inMemoryTables = MutableStateFlow(tables)

    override fun getAll(): Flow<List<DatabaseTable>> = inMemoryTables

    override suspend fun tableCount(): Int = inMemoryTables.value.size

    override suspend fun insertTables(tables: List<DatabaseTable>) {
        inMemoryTables.value = tables
    }

}

class FakeTableRemoteService(private val tables: List<RemoteTable> = emptyList()) :
    TableRemoteService {

    override suspend fun getTablesData(user: String): List<RemoteTable> = tables
}