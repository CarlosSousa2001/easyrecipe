package com.crs.receitafacil.core.domain.exceptions

import com.crs.receitafacil.core.data.remote.response.ErrorResponse

class ErrorResponseException(val error: ErrorResponse): RuntimeException() {
}