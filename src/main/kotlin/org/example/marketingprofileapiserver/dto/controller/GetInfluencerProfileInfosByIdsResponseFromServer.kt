package org.example.marketingprofileapiserver.dto.controller

import org.example.marketingprofileapiserver.dto.MSABusinessErrorResponse
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.dto.service.GetInfluencerProfileInfosByIdsResult
import org.springframework.http.HttpStatus

data class GetInfluencerProfileInfosByIdsResponseFromServer(
    val getInfluencerProfileInfosByIdsResult: GetInfluencerProfileInfosByIdsResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: GetInfluencerProfileInfosByIdsResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): GetInfluencerProfileInfosByIdsResponseFromServer {
            return GetInfluencerProfileInfosByIdsResponseFromServer(
                getInfluencerProfileInfosByIdsResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
