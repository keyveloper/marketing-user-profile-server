package org.example.marketinguserprofileserver.dto.controller

import org.example.marketinguserprofileserver.dto.MSABusinessErrorResponse
import org.example.marketinguserprofileserver.dto.service.UpdateAdvertiserProfileInfoResult
import org.example.marketinguserprofileserver.enums.MSAServiceErrorCode
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
