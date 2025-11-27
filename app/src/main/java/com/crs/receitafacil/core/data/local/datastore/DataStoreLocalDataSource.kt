package com.crs.receitafacil.core.data.local.datastore

import com.crs.receitafacil.core.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface DataStoreLocalDataSource {
    fun getData(): Flow<UserData>
    suspend fun saveData(token: String, userName: String)
    suspend fun clearAll()
}