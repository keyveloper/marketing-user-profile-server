package org.example.marketingprofileapiserver.dto.controller

import org.example.marketingprofileapiserver.dto.MSABusinessErrorResponse
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.dto.service.GetInfluencerProfileInfoResult
import org.springframework.http.HttpStatus

data class GetInfluencerProfileInfoResponseFromServer(
    val getInfluencerProfileInfoResult: GetInfluencerProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: GetInfluencerProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): GetInfluencerProfileInfoResponseFromServer {
            return GetInfluencerProfileInfoResponseFromServer(
                getInfluencerProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
