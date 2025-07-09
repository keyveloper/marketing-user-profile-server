package org.example.marketinguserprofileserver.dto.controller

import org.example.marketinguserprofileserver.dto.MSABusinessErrorResponse
import org.example.marketinguserprofileserver.dto.service.GetInfluencerProfileInfoResult
import org.example.marketinguserprofileserver.enums.MSAServiceErrorCode
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
