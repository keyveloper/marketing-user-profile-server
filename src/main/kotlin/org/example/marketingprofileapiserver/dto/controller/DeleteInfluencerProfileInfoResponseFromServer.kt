package org.example.marketingprofileapiserver.dto.controller

import org.example.marketingprofileapiserver.dto.MSABusinessErrorResponse
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.dto.service.DeleteInfluencerProfileInfoResult
import org.springframework.http.HttpStatus

data class DeleteInfluencerProfileInfoResponseFromServer(
    val deleteInfluencerProfileInfoResult: DeleteInfluencerProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: DeleteInfluencerProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): DeleteInfluencerProfileInfoResponseFromServer {
            return DeleteInfluencerProfileInfoResponseFromServer(
                deleteInfluencerProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
