package org.example.marketingprofileapiserver.dto.controller

import org.example.marketingprofileapiserver.dto.MSABusinessErrorResponse
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.dto.service.GetAdvertiserProfileInfosByIdsResult
import org.springframework.http.HttpStatus

data class GetAdvertiserProfileInfosByIdsResponseFromServer(
    val getAdvertiserProfileInfosByIdsResult: GetAdvertiserProfileInfosByIdsResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: GetAdvertiserProfileInfosByIdsResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): GetAdvertiserProfileInfosByIdsResponseFromServer {
            return GetAdvertiserProfileInfosByIdsResponseFromServer(
                getAdvertiserProfileInfosByIdsResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
