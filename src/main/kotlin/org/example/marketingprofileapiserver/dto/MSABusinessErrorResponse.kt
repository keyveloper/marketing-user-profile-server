package org.example.marketingprofileapiserver.dto

import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.springframework.http.HttpStatus

open class MSABusinessErrorResponse(
    open val httpStatus: HttpStatus,
    open val msaServiceErrorCode: MSAServiceErrorCode,
    open val errorMessage: String? = null,
    open val logics: String? = null,
) {
    companion object {
        fun of(
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
            errorMessage: String?,
            logics: String
        ): MSABusinessErrorResponse {
            return MSABusinessErrorResponse(
                httpStatus,
                msaServiceErrorCode,
                errorMessage,
                logics
            )
        }
    }
}
