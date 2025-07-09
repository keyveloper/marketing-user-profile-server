package org.example.marketinguserprofileserver.controller

import org.example.marketinguserprofileserver.dto.InfluencerProfile
import org.example.marketinguserprofileserver.dto.controller.*
import org.example.marketinguserprofileserver.enums.MSAServiceErrorCode
import org.example.marketinguserprofileserver.service.InfluencerProfileInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/influencer-profiles")
class InfluencerProfileInfoController(
    private val influencerProfileInfoService: InfluencerProfileInfoService
) {

    @PostMapping
    fun saveInfluencerProfileInfo(
        @RequestBody request: SaveInfluencerProfileInfoRequest
    ): ResponseEntity<SaveInfluencerProfileInfoResponseFromServer> {
        val domain = InfluencerProfile(
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

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun getInfluencerProfileInfoById(
        @PathVariable id: Long
    ): ResponseEntity<GetInfluencerProfileInfoResponseFromServer> {
        val getInfluencerProfileInfoResult = influencerProfileInfoService.getInfluencerProfileInfoById(id)

        if (getInfluencerProfileInfoResult == null) {
            val errorResponse = GetInfluencerProfileInfoResponseFromServer(
                getInfluencerProfileInfoResult = null,
                httpStatus = HttpStatus.NOT_FOUND,
                msaServiceErrorCode = MSAServiceErrorCode.NOT_FOUND_INFLUENCER_PROFILE,
                errorMessage = "Influencer profile not found with id: $id"
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

    @PutMapping("/{id}")
    fun updateInfluencerProfileInfoById(
        @PathVariable id: Long,
        @RequestBody request: UpdateInfluencerProfileInfoRequest
    ): ResponseEntity<UpdateInfluencerProfileInfoResponseFromServer> {
        val domain = InfluencerProfile(
            influencerId = request.influencerId,
            introduction = request.introduction,
            job = request.job
        )

        val updateInfluencerProfileInfoResult = influencerProfileInfoService.updateInfluencerProfileInfoById(id, domain)

        val response = UpdateInfluencerProfileInfoResponseFromServer.of(
            result = updateInfluencerProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteInfluencerProfileInfoById(
        @PathVariable id: Long
    ): ResponseEntity<DeleteInfluencerProfileInfoResponseFromServer> {
        val deleteInfluencerProfileInfoResult = influencerProfileInfoService.deleteInfluencerProfileInfoById(id)

        val response = DeleteInfluencerProfileInfoResponseFromServer.of(
            result = deleteInfluencerProfileInfoResult,
            httpStatus = HttpStatus.OK,
            msaServiceErrorCode = MSAServiceErrorCode.OK
        )

        return ResponseEntity.ok(response)
    }
}
