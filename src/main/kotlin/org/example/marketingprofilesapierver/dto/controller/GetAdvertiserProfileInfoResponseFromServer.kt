package org.example.marketingprofilesapierver.dto.controller

import org.example.marketingprofilesapierver.dto.MSABusinessErrorResponse
import org.example.marketingprofilesapierver.enums.MSAServiceErrorCode
import org.example.marketingprofilesapierver.dto.service.GetAdvertiserProfileInfoResult
import org.springframework.http.HttpStatus

data class GetAdvertiserProfileInfoResponseFromServer(
    val getAdvertiserProfileInfoResult: GetAdvertiserProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: GetAdvertiserProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): GetAdvertiserProfileInfoResponseFromServer {
            return GetAdvertiserProfileInfoResponseFromServer(
                getAdvertiserProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
