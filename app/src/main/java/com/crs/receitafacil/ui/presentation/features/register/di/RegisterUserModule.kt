package com.crs.receitafacil.ui.presentation.features.register.di

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.ui.presentation.features.register.data.repository.RegisterUseRepositoryImpl
import com.crs.receitafacil.ui.presentation.features.register.data.source.RegisterUserRemoteDataSourceImpl
import com.crs.receitafacil.ui.presentation.features.register.domain.repository.RegisterUserRepository
import com.crs.receitafacil.ui.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import com.crs.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUseCase
import com.crs.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.crs.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterUserModule {

    @Provides
    @Singleton
    fun provideRegisterUserDataSource(
        recipesServiceApi: RecipeServiceApi
    ): RegisterUserRemoteDataSource {
        return RegisterUserRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)
    }

    @Provides
    @Singleton
    fun provideRegisterUserRepository(
        registerUserRemoteDataSource: RegisterUserRemoteDataSource
    ): RegisterUserRepository {
        return RegisterUseRepositoryImpl(remoteDataSource = registerUserRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providerRegisterUserUseCase(
        registerUserRepository: RegisterUserRepository,
        dispatchersProvider: DispatchersProvider
    ): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(
            registerUserRepository = registerUserRepository,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(
    ): ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCaseImpl()
    }

}