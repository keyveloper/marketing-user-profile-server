package org.example.marketinguserprofileserver.dto.controller

import org.example.marketinguserprofileserver.dto.MSABusinessErrorResponse
import org.example.marketinguserprofileserver.dto.service.DeleteAdvertiserProfileInfoResult
import org.example.marketinguserprofileserver.enums.MSAServiceErrorCode
import org.springframework.http.HttpStatus

data class DeleteAdvertiserProfileInfoResponseFromServer(
    val deleteAdvertiserProfileInfoResult: DeleteAdvertiserProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: DeleteAdvertiserProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): DeleteAdvertiserProfileInfoResponseFromServer {
            return DeleteAdvertiserProfileInfoResponseFromServer(
                deleteAdvertiserProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
