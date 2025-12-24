package com.crs.receitafacil.ui.presentation.features.recipes.add.di

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.ui.presentation.features.recipes.add.data.repository.AddUpdateRecipeRepositoryImpl
import com.crs.receitafacil.ui.presentation.features.recipes.add.data.source.AddUpdateRecipeRemoteDataSourceImpl
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.repository.AddUpdateRecipeRepository
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.source.AddUpdateRecipeRemoteDataSource
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.AddRecipeUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.AddRecipeUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.UpdateRecipeUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.UpdateRecipeUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.ValidateAddUpdateRecipeInputUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.ValidateAddUpdateRecipeInputUseCaseImpl
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.ValidateDialogInputUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.ValidateDialogInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddUpdateRecipeModule {

    @Provides
    @Singleton
    fun provideAddUpdateRecipeRemoteDataSource(
        serviceApi: RecipeServiceApi,
    ): AddUpdateRecipeRemoteDataSource {
        return AddUpdateRecipeRemoteDataSourceImpl(
            serviceApi = serviceApi
        )
    }

    @Provides
    @Singleton
    fun provideAddUpdateRecipeRepository(
        remoteDataSource: AddUpdateRecipeRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): AddUpdateRecipeRepository {
        return AddUpdateRecipeRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideUpdateRecipeUseCase(
        addUpdateRecipeRepository: AddUpdateRecipeRepository
    ): UpdateRecipeUseCase {
        return UpdateRecipeUseCaseImpl(
            addUpdateRecipeRepository = addUpdateRecipeRepository
        )
    }


    @Provides
    @Singleton
    fun provideAddRecipeUseCase(
        addUpdateRecipeRepository: AddUpdateRecipeRepository
    ): AddRecipeUseCase {
        return AddRecipeUseCaseImpl(
            addUpdateRecipeRepository = addUpdateRecipeRepository
        )
    }

    @Provides
    @Singleton
    fun provideValidateAddUpdateRecipeInputUseCase(): ValidateAddUpdateRecipeInputUseCase {
        return ValidateAddUpdateRecipeInputUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideValidateDialogInputUseCase(): ValidateDialogInputUseCase {
        return ValidateDialogInputUseCaseImpl()
    }

}