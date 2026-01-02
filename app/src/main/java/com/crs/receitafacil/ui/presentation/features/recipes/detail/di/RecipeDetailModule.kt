package com.crs.receitafacil.ui.presentation.features.recipes.detail.di

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.ui.presentation.features.recipes.detail.data.repository.RecipeDetailRepositoryImpl
import com.crs.receitafacil.ui.presentation.features.recipes.detail.data.source.RecipeDetailsRemoteDataSourceImpl
import com.crs.receitafacil.ui.presentation.features.recipes.detail.domain.repository.RecipeDetailRepository
import com.crs.receitafacil.ui.presentation.features.recipes.detail.domain.source.RecipeDetailsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeDetailModule
{

    @Provides
    @Singleton
    fun provideRecipeDetailRemoteDataSource(
        serviceApi: RecipeServiceApi
    ): RecipeDetailsRemoteDataSource {
        return RecipeDetailsRemoteDataSourceImpl(serviceApi)
    }

    @Provides
    @Singleton
    fun provideRecipeDetailRepository(
        remoteDataSource: RecipeDetailsRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): RecipeDetailRepository {
        return RecipeDetailRepositoryImpl(remoteDataSource, dispatchersProvider)
    }

}