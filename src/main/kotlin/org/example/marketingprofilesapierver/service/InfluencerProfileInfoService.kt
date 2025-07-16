package org.example.marketingprofilesapierver.service

import org.example.marketingprofilesapierver.dto.InfluencerProfile
import org.example.marketingprofilesapierver.dto.service.DeleteInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.dto.service.GetInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.dto.service.SaveInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.dto.service.UpdateInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.enums.MSAServiceErrorCode
import org.example.marketingprofilesapierver.exception.MSAServerException
import org.example.marketingprofilesapierver.repository.InfluencerProfileInfoRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InfluencerProfileInfoService(
    private val influencerProfileInfoRepository: InfluencerProfileInfoRepository
) {

    fun saveInfluencerProfileInfo(domain: InfluencerProfile): SaveInfluencerProfileInfoResult {
        return transaction {
            val savedId = influencerProfileInfoRepository.save(domain)
            SaveInfluencerProfileInfoResult(savedEntityId = savedId)
        }
    }

    fun getInfluencerProfileInfoById(influencerIdStr: String): GetInfluencerProfileInfoResult? {
        val influencerId = try {
            UUID.fromString(influencerIdStr)
        } catch (e: IllegalArgumentException) {
            throw MSAServerException(
                httpStatus = HttpStatus.BAD_REQUEST,
                msaServiceErrorCode = MSAServiceErrorCode.INVALID_UUID_FORMAT,
                logics = "InfluencerProfileInfoService.getInfluencerProfileInfoById",
                message = "Invalid UUID format: $influencerIdStr"
            )
        }
        return transaction {
            val entity = influencerProfileInfoRepository.findByInfluencerId(influencerId) ?: return@transaction null
            GetInfluencerProfileInfoResult(
                id = entity.id.value,
                userProfileDraftId = entity.userProfileDraftId,
                influencerId = entity.influencerId,
                introduction = entity.introduction,
                job = entity.job,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    fun updateInfluencerProfileInfoById(influencerIdStr: String, domain: InfluencerProfile): UpdateInfluencerProfileInfoResult {
        val influencerId = try {
            UUID.fromString(influencerIdStr)
        } catch (e: IllegalArgumentException) {
            throw MSAServerException(
                httpStatus = HttpStatus.BAD_REQUEST,
                msaServiceErrorCode = MSAServiceErrorCode.INVALID_UUID_FORMAT,
                logics = "InfluencerProfileInfoService.updateInfluencerProfileInfoById",
                message = "Invalid UUID format: $influencerIdStr"
            )
        }
        return transaction {
            val updatedCount = influencerProfileInfoRepository.updateByInfluencerId(influencerId, domain)
            UpdateInfluencerProfileInfoResult(updatedCount = updatedCount)
        }
    }

    fun deleteInfluencerProfileInfoById(influencerIdStr: String): DeleteInfluencerProfileInfoResult {
        val influencerId = try {
            UUID.fromString(influencerIdStr)
        } catch (e: IllegalArgumentException) {
            throw MSAServerException(
                httpStatus = HttpStatus.BAD_REQUEST,
                msaServiceErrorCode = MSAServiceErrorCode.INVALID_UUID_FORMAT,
                logics = "InfluencerProfileInfoService.deleteInfluencerProfileInfoById",
                message = "Invalid UUID format: $influencerIdStr"
            )
        }
        return transaction {
            val deletedCount = influencerProfileInfoRepository.deleteByInfluencerId(influencerId)
            DeleteInfluencerProfileInfoResult(deletedCount = deletedCount)
        }
    }
}
