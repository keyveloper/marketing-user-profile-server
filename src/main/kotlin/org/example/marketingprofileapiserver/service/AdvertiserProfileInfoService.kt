package org.example.marketingprofileapiserver.service

import org.example.marketingprofileapiserver.dto.AdvertiserProfile
import org.example.marketingprofileapiserver.dto.service.*
import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.example.marketingprofileapiserver.exception.MSAServerException
import org.example.marketingprofileapiserver.repository.AdvertiserProfileInfoRepository
import org.example.marketingprofileapiserver.dto.service.UpdateAdvertiserProfileInfoResult
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AdvertiserProfileInfoService(
    private val advertiserProfileInfoRepository: AdvertiserProfileInfoRepository
) {

    fun saveAdvertiserProfileInfo(domain: AdvertiserProfile): SaveAdvertiserProfileInfoResult {
        return transaction {
            val savedId = advertiserProfileInfoRepository.save(domain)
            SaveAdvertiserProfileInfoResult(savedEntityId = savedId)
        }
    }

    fun getAdvertiserProfileInfoById(advertiserIdStr: String): GetAdvertiserProfileInfoResult? {
        val advertiserId = try {
            UUID.fromString(advertiserIdStr)
        } catch (e: IllegalArgumentException) {
            throw MSAServerException(
                httpStatus = HttpStatus.BAD_REQUEST,
                msaServiceErrorCode = MSAServiceErrorCode.INVALID_UUID_FORMAT,
                logics = "AdvertiserProfileInfoService.getAdvertiserProfileInfoById",
                message = "Invalid UUID format: $advertiserIdStr"
            )
        }
        return transaction {
            val entity = advertiserProfileInfoRepository.findByAdvertiserId(advertiserId) ?: return@transaction null
            GetAdvertiserProfileInfoResult(
                id = entity.id.value,
                advertiserId = entity.advertiserId,
                userProfileDraftId = entity.userProfileDraftId,
                serviceInfo = entity.serviceInfo,
                locationBrief = entity.locationBrief,
                introduction = entity.introduction,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    fun updateAdvertiserProfileInfoById(advertiserIdStr: String, domain: AdvertiserProfile)
    : UpdateAdvertiserProfileInfoResult {
        val advertiserId = try {
            UUID.fromString(advertiserIdStr)
        } catch (e: IllegalArgumentException) {
            throw MSAServerException(
                httpStatus = HttpStatus.BAD_REQUEST,
                msaServiceErrorCode = MSAServiceErrorCode.INVALID_UUID_FORMAT,
                logics = "AdvertiserProfileInfoService.updateAdvertiserProfileInfoById",
                message = "Invalid UUID format: $advertiserIdStr"
            )
        }
        return transaction {
            val updatedCount = advertiserProfileInfoRepository.updateByAdvertiserId(advertiserId, domain)
            UpdateAdvertiserProfileInfoResult(updatedCount = updatedCount)
        }
    }

    fun deleteAdvertiserProfileInfoById(advertiserIdStr: String): DeleteAdvertiserProfileInfoResult {
        val advertiserId = try {
            UUID.fromString(advertiserIdStr)
        } catch (e: IllegalArgumentException) {
            throw MSAServerException(
                httpStatus = HttpStatus.BAD_REQUEST,
                msaServiceErrorCode = MSAServiceErrorCode.INVALID_UUID_FORMAT,
                logics = "AdvertiserProfileInfoService.deleteAdvertiserProfileInfoById",
                message = "Invalid UUID format: $advertiserIdStr"
            )
        }
        return transaction {
            val deletedCount = advertiserProfileInfoRepository.deleteByAdvertiserId(advertiserId)
            DeleteAdvertiserProfileInfoResult(deletedCount = deletedCount)
        }
    }
}
