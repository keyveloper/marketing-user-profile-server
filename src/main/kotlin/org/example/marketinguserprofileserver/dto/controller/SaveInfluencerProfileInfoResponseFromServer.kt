package org.example.marketinguserprofileserver.dto.controller

import org.example.marketinguserprofileserver.dto.MSABusinessErrorResponse
import org.example.marketinguserprofileserver.dto.service.SaveInfluencerProfileInfoResult
import org.example.marketinguserprofileserver.enums.MSAServiceErrorCode
import org.springframework.http.HttpStatus

data class SaveInfluencerProfileInfoResponseFromServer(
    val saveInfluencerProfileInfoResult: SaveInfluencerProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: SaveInfluencerProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): SaveInfluencerProfileInfoResponseFromServer {
            return SaveInfluencerProfileInfoResponseFromServer(
                saveInfluencerProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
