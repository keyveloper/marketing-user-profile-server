package org.example.marketingprofilesapierver.dto.controller

import org.example.marketingprofilesapierver.dto.MSABusinessErrorResponse
import org.example.marketingprofilesapierver.enums.MSAServiceErrorCode
import org.example.marketingprofilesapierver.dto.service.SaveAdvertiserProfileInfoResult
import org.springframework.http.HttpStatus

data class SaveAdvertiserProfileInfoResponseFromServer(
    val saveAdvertiserProfileInfoResult: SaveAdvertiserProfileInfoResult?,
    override val httpStatus: HttpStatus,
    override val msaServiceErrorCode: MSAServiceErrorCode,
    override val errorMessage: String? = null,
    override val logics: String? = null
): MSABusinessErrorResponse(httpStatus, msaServiceErrorCode) {
    companion object {
        fun of(
            result: SaveAdvertiserProfileInfoResult,
            httpStatus: HttpStatus,
            msaServiceErrorCode: MSAServiceErrorCode,
        ): SaveAdvertiserProfileInfoResponseFromServer {
            return SaveAdvertiserProfileInfoResponseFromServer(
                saveAdvertiserProfileInfoResult = result,
                httpStatus = httpStatus,
                msaServiceErrorCode = msaServiceErrorCode,
            )
        }
    }
}
