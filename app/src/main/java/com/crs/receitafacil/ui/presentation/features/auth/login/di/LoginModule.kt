package com.crs.receitafacil.ui.presentation.features.auth.login.di

import com.crs.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.ui.presentation.features.auth.login.data.repository.LoginRepositoryImpl
import com.crs.receitafacil.ui.presentation.features.auth.login.data.source.LoginRemoteDataSourceImpl
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideLoginRemoteDataSource(
        recipeServiceApi: RecipeServiceApi
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(recipeServiceApi = recipeServiceApi)
    }

    @Provides
    fun provideLoginRepository(
        remoteDataSource: LoginRemoteDataSource,
        localDataSource: DataStoreLocalDataSource,
        dispatchersProvider: DispatchersProvider
    ): LoginRepository {
        return LoginRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    fun providesLoginUseCase(
        loginRepository: LoginRepository,
        dispatchersProvider: DispatchersProvider
    ): LoginUseCase {
        return LoginUseCaseImpl(
            dispatchersProvider = dispatchersProvider,
            loginRepository = loginRepository
        )
    }

    @Provides
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }

    @Provides
    fun provideGetUserDataCase(repository: LoginRepository): GetUserDataUseCase {
        return GetUserDataUseCaseImpl(repository)
    }


    @Provides
    fun provideSaveUserDataUseCase(
        repository: LoginRepository,
        dispatchersProvider: DispatchersProvider
    ): SaveUserDataUseCase {
        return SaveUserDataUseCaseImpl(
            loginRepository = repository,
        )
    }

    @Provides
    fun provideRemoveUserDataUseCase(
        repository: LoginRepository,
    ): RemoveUserDataUseCase {
        return RemoveUserDataUseCaseImpl(
            loginRepository = repository
        )
    }
}
