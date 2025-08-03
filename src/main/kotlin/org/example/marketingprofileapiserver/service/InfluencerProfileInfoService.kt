package org.example.marketingprofileapiserver.service

import org.example.marketingprofileapiserver.dto.UpdateInfluencerProfile
import org.example.marketingprofileapiserver.dto.service.*
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.exception.MSAServerException
import org.example.marketingprofileapiserver.repository.InfluencerProfileInfoRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InfluencerProfileInfoService(
    private val influencerProfileInfoRepository: InfluencerProfileInfoRepository
) {

    fun saveInfluencerProfileInfo(domain: UpdateInfluencerProfile): SaveInfluencerProfileInfoResult {
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
            val info = influencerProfileInfoRepository.findByInfluencerId(influencerId) ?: return@transaction null
            GetInfluencerProfileInfoResult(
                id = info.id,
                userProfileDraftId = info.userProfileDraftId,
                influencerName = info.influencerName,
                influencerId = info.influencerId,
                introduction = info.introduction,
                job = info.job,
                createdAt = info.createdAt,
                updatedAt = info.updatedAt
            )
        }
    }

    fun updateInfluencerProfileInfoById(influencerIdStr: String, domain: UpdateInfluencerProfile): UpdateInfluencerProfileInfoResult {
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

    fun getInfluencerProfileInfosByIds(influencerIds: List<UUID>): GetInfluencerProfileInfosByIdsResult {
        return transaction {
            val infos = influencerProfileInfoRepository.findAllByInfluencerIds(influencerIds)
            GetInfluencerProfileInfosByIdsResult(influencerProfileInfos = infos)
        }
    }
}
