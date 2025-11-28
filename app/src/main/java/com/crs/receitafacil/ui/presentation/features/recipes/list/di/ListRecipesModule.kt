package com.crs.receitafacil.ui.presentation.features.recipes.list.di

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.ui.presentation.features.recipes.list.data.repository.GetRecipesByUserRepositoryImpl
import com.crs.receitafacil.ui.presentation.features.recipes.list.data.source.GetRecipesByUserRemoteDataSourceImpl
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.usecase.GetRecipesByUserUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.usecase.GetRecipesByUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListRecipesModule {

    @Provides
    @Singleton
    fun providerGetRecipesByUserRemoteDataSource(
        recipeServiceApi: RecipeServiceApi
    ): GetRecipesByUserRemoteDataSource {
        return GetRecipesByUserRemoteDataSourceImpl(
            recipeServiceApi = recipeServiceApi
        )
    }

    @Provides
    @Singleton
    fun provideGetRecipeByUserRepository(
        remoteDataSource: GetRecipesByUserRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): GetRecipesByUserRepository {
        return GetRecipesByUserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideGetRecipesByUserUseCase(
        getRecipesByUserRepository: GetRecipesByUserRepository
    ): GetRecipesByUserUseCase {
        return GetRecipesByUserUseCaseImpl(
            getRecipesByUserRepository = getRecipesByUserRepository
        )
    }

}
