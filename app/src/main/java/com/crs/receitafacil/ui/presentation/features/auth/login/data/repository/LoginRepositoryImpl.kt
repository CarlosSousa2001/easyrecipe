package com.crs.receitafacil.ui.presentation.features.auth.login.data.repository

import com.crs.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.receitafacil.core.domain.model.UserData
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatchersProvider: DispatchersProvider
) : LoginRepository {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.login(authUserRequestModel)
        }
    }

    override fun getData(): Flow<UserData> = localDataSource.getData()

    override suspend fun saveData(token: String, userName: String) {
      return  withContext(dispatchersProvider.io()) {
           localDataSource.saveData(token = token, userName = userName)
      }
    }

    override suspend fun clearAll() {
        return withContext(dispatchersProvider.io()) {
            localDataSource.clearAll()
        }
    }
}