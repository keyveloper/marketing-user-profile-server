package org.example.marketingprofilesapierver.controller

import org.example.marketingprofilesapierver.dto.InfluencerProfile
import org.example.marketingprofilesapierver.dto.controller.*
import org.example.marketingprofilesapierver.enums.MSAServiceErrorCode
import org.example.marketingprofilesapierver.service.InfluencerProfileInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/influencer-profiles")
class InfluencerProfileInfoController(
    private val influencerProfileInfoService: InfluencerProfileInfoService
) {

    @PostMapping
    fun saveInfluencerProfileInfo(
        @RequestBody request: SaveInfluencerProfileInfoApiRequest
    ): ResponseEntity<SaveInfluencerProfileInfoResponseFromServer> {
        val domain = InfluencerProfile(
            userProfileDraftId = request.userProfileDraftId,
            influencerId = request.influencerId,
            introduction = request.introduction,
            job = request.job
        )

        val saveInfluencerProfileInfoResult = influencerProfileInfoService.saveInfluencerProfileInfo(domain)

        val response = SaveInfluencerProfileInfoResponseFromServer.of(
            result = saveInfluencerProfileInfoResult,
            httpStatus = HttpStatus.CREATED,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{influencerId}")
    fun getInfluencerProfileInfoById(
        @PathVariable influencerId: String
    ): ResponseEntity<GetInfluencerProfileInfoResponseFromServer> {
        val getInfluencerProfileInfoResult = influencerProfileInfoService.getInfluencerProfileInfoById(influencerId)

        if (getInfluencerProfileInfoResult == null) {
            val errorResponse = GetInfluencerProfileInfoResponseFromServer(
                getInfluencerProfileInfoResult = null,
                httpStatus = HttpStatus.NOT_FOUND,
                msaServiceErrorCode = MSAServiceErrorCode.NOT_FOUND_INFLUENCER_PROFILE,
                errorMessage = "Influencer profile not found with influencerId: $influencerId"
            )
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
        }

        val response = GetInfluencerProfileInfoResponseFromServer.of(
            result = getInfluencerProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }

    @PutMapping("/{influencerId}")
    fun updateInfluencerProfileInfoById(
        @PathVariable influencerId: String,
        @RequestBody request: UpdateInfluencerProfileInfoApiRequest
    ): ResponseEntity<UpdateInfluencerProfileInfoResponseFromServer> {
        val domain = InfluencerProfile(
            userProfileDraftId = request.userProfileDraftId,
            influencerId = request.influencerId,
            introduction = request.introduction,
            job = request.job
        )

        val updateInfluencerProfileInfoResult = influencerProfileInfoService.updateInfluencerProfileInfoById(influencerId, domain)

        val response = UpdateInfluencerProfileInfoResponseFromServer.of(
            result = updateInfluencerProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{influencerId}")
    fun deleteInfluencerProfileInfoById(
        @PathVariable influencerId: String
    ): ResponseEntity<DeleteInfluencerProfileInfoResponseFromServer> {
        val deleteInfluencerProfileInfoResult = influencerProfileInfoService.deleteInfluencerProfileInfoById(influencerId)

        val response = DeleteInfluencerProfileInfoResponseFromServer.of(
            result = deleteInfluencerProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }
}
