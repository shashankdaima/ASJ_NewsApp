package com.example.androidstudyjam1.utils

import kotlinx.coroutines.flow.Flow

interface CachingStrategy<Input, NetworkModel, DatabaseModel, DomainModel> {
    fun query(input: Input? = null): Flow<DatabaseModel>
    suspend fun fetch(input: Input? = null): Response<NetworkModel>
    suspend fun saveFetchResult(data: DatabaseModel)
    suspend fun clearPreviousData()
    suspend fun shouldFetch(data: DatabaseModel): Boolean
    fun networkAndDatabaseMapper(data: NetworkModel?): DatabaseModel
    fun databaseAndDomainMapper(data: DatabaseModel): DomainModel
    operator fun invoke(input: Input?)
}