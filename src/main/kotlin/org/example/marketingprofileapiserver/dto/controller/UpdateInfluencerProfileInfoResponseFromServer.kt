package org.example.marketingprofileapiserver.dto.controller

import org.example.marketingprofileapiserver.dto.MSABusinessErrorResponse
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.dto.service.UpdateInfluencerProfileInfoResult
import org.springframework.http.HttpStatus

data class UpdateInfluencerProfileInfoResponseFromServer(
    val updateInfluencerProfileInfoResult: UpdateInfluencerProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: UpdateInfluencerProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): UpdateInfluencerProfileInfoResponseFromServer {
            return UpdateInfluencerProfileInfoResponseFromServer(
                updateInfluencerProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
