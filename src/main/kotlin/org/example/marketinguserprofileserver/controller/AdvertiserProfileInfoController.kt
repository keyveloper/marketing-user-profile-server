package org.example.marketinguserprofileserver.controller

import org.example.marketinguserprofileserver.dto.AdvertiserProfile
import org.example.marketinguserprofileserver.dto.controller.*
import org.example.marketinguserprofileserver.enums.MSAServiceErrorCode
import org.example.marketinguserprofileserver.service.AdvertiserProfileInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/advertiser-profiles")
class AdvertiserProfileInfoController(
    private val advertiserProfileInfoService: AdvertiserProfileInfoService
) {

    @PostMapping
    fun saveAdvertiserProfileInfo(
        @RequestBody request: SaveAdvertiserProfileInfoRequest
    ): ResponseEntity<SaveAdvertiserProfileInfoResponseFromServer> {
        val domain = AdvertiserProfile(
            advertiserId = request.advertiserId,
            serviceInfo = request.serviceInfo,
            locationBrief = request.locationBrief,
            introduction = request.introduction
        )

        val saveAdvertiserProfileInfoResult = advertiserProfileInfoService.saveAdvertiserProfileInfo(domain)

        val response = SaveAdvertiserProfileInfoResponseFromServer.of(
            result = saveAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.CREATED,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun getAdvertiserProfileInfoById(
        @PathVariable id: Long
    ): ResponseEntity<GetAdvertiserProfileInfoResponseFromServer> {
        val getAdvertiserProfileInfoResult = advertiserProfileInfoService.getAdvertiserProfileInfoById(id)

        if (getAdvertiserProfileInfoResult == null) {
            val errorResponse = GetAdvertiserProfileInfoResponseFromServer(
                getAdvertiserProfileInfoResult = null,
                httpStatus = HttpStatus.NOT_FOUND,
                msaServiceErrorCode = MSAServiceErrorCode.NOT_FOUND_ADVERTISER_PROFILE,
                errorMessage = "Advertiser profile not found with id: $id"
            )
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
        }

        val response = GetAdvertiserProfileInfoResponseFromServer.of(
            result = getAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updateAdvertiserProfileInfoById(
        @PathVariable id: Long,
        @RequestBody request: UpdateAdvertiserProfileInfoRequest
    ): ResponseEntity<UpdateAdvertiserProfileInfoResponseFromServer> {
        val domain = AdvertiserProfile(
            advertiserId = request.advertiserId,
            serviceInfo = request.serviceInfo,
            locationBrief = request.locationBrief,
            introduction = request.introduction
        )

        val updateAdvertiserProfileInfoResult = advertiserProfileInfoService.updateAdvertiserProfileInfoById(id, domain)

        val response = UpdateAdvertiserProfileInfoResponseFromServer.of(
            result = updateAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteAdvertiserProfileInfoById(
        @PathVariable id: Long
    ): ResponseEntity<DeleteAdvertiserProfileInfoResponseFromServer> {
        val deleteAdvertiserProfileInfoResult = advertiserProfileInfoService.deleteAdvertiserProfileInfoById(id)

        val response = DeleteAdvertiserProfileInfoResponseFromServer.of(
            result = deleteAdvertiserProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }
}
