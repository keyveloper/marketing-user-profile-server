package org.example.marketingprofilesapierver.dto.controller

import org.example.marketingprofilesapierver.dto.MSABusinessErrorResponse
import org.example.marketingprofilesapierver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.dto.service.UpdateAdvertiserProfileInfoResult
import org.springframework.http.HttpStatus

data class UpdateAdvertiserProfileInfoResponseFromServer(
    val updateAdvertiserProfileInfoResult: UpdateAdvertiserProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: UpdateAdvertiserProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): UpdateAdvertiserProfileInfoResponseFromServer {
            return UpdateAdvertiserProfileInfoResponseFromServer(
                updateAdvertiserProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
