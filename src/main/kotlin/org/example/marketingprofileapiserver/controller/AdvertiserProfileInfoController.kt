package org.example.marketingprofileapiserver.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.example.marketingprofileapiserver.dto.AdvertiserProfile
import org.example.marketingprofileapiserver.dto.controller.*
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.service.AdvertiserProfileInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/v1/advertiser-profiles")
class AdvertiserProfileInfoController(
    private val advertiserProfileInfoService: AdvertiserProfileInfoService
) {

    @PostMapping
    fun saveAdvertiserProfileInfo(
        @RequestBody request: SaveAdvertiserProfileInfoApiRequest
    ): ResponseEntity<SaveAdvertiserProfileInfoResponseFromServer> {
        logger.info { "saveAdvertiserProfileInfo called with request: $request" }

        val domain = AdvertiserProfile(
            advertiserId = request.advertiserId,
            advertiserName = request.advertiserName,
            userProfileDraftId = request.userProfileDraftId,
            serviceInfo = request.serviceInfo,
            locationBrief = request.locationBrief,
            introduction = request.introduction
        )

        val saveAdvertiserProfileInfoResult = advertiserProfileInfoService.saveAdvertiserProfileInfo(domain)

        return ResponseEntity.ok(
            SaveAdvertiserProfileInfoResponseFromServer.of(
                result = saveAdvertiserProfileInfoResult,
                httpStatus = HttpStatus.OK,
                msaServiceErrorCode = MSAServiceErrorCode.OK
            )
        )
    }

    @GetMapping("/{advertiserId}")
    fun getAdvertiserProfileInfoById(
        @PathVariable advertiserId: String
    ): ResponseEntity<GetAdvertiserProfileInfoResponseFromServer> {
        val getAdvertiserProfileInfoResult = advertiserProfileInfoService.getAdvertiserProfileInfoById(advertiserId)

        if (getAdvertiserProfileInfoResult == null) {
            val errorResponse = GetAdvertiserProfileInfoResponseFromServer(
                getAdvertiserProfileInfoResult = null,
                httpStatus = HttpStatus.NOT_FOUND,
                msaServiceErrorCode = MSAServiceErrorCode.NOT_FOUND_ADVERTISER_PROFILE,
                errorMessage = "Advertiser profile not found with advertiserId: $advertiserId"
            )
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
        }

        val response = GetAdvertiserProfileInfoResponseFromServer.of(
            result = getAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok().body(response)
    }

    @PutMapping("/{advertiserId}")
    fun updateAdvertiserProfileInfoById(
        @PathVariable advertiserId: String,
        @RequestBody request: UpdateAdvertiserProfileInfoApiRequest
    ): ResponseEntity<UpdateAdvertiserProfileInfoResponseFromServer> {
        val domain = AdvertiserProfile(
            advertiserId = request.advertiserId,
            advertiserName = request.advertiserName,
            userProfileDraftId = request.userProfileDraftId,
            serviceInfo = request.serviceInfo,
            locationBrief = request.locationBrief,
            introduction = request.introduction
        )

        val updateAdvertiserProfileInfoResult = advertiserProfileInfoService.updateAdvertiserProfileInfoById(advertiserId, domain)

        val response = UpdateAdvertiserProfileInfoResponseFromServer.of(
            result = updateAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok().body(response)
    }

    @DeleteMapping("/{advertiserId}")
    fun deleteAdvertiserProfileInfoById(
        @PathVariable advertiserId: String
    ): ResponseEntity<DeleteAdvertiserProfileInfoResponseFromServer> {
        val deleteAdvertiserProfileInfoResult = advertiserProfileInfoService.deleteAdvertiserProfileInfoById(advertiserId)

        val response = DeleteAdvertiserProfileInfoResponseFromServer.of(
            result = deleteAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok().body(response)
    }
}
