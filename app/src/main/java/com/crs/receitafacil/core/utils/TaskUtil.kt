package com.crs.receitafacil.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class Task<in Parameters, Result> {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<Result>> = flow {
        emit(ResponseData.Loading)
        emit(executeTask(parameters))
    }.catch { throwable ->
        emit(ResponseData.Error(throwable))
    }
    protected abstract suspend fun executeTask(parameters: Parameters): ResponseData<Result>
}

abstract class FlowTask<in Parameters, Result: Any> {
    suspend operator fun invoke(parameters: Parameters): Flow<Result> = executeTaskFlow(parameters)
    protected abstract suspend fun executeTaskFlow(parameters: Parameters): Flow<Result>
}
