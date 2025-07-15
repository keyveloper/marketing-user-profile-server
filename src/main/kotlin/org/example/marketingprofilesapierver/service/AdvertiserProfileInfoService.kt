package org.example.marketingprofilesapierver.service

import org.example.marketingprofilesapierver.dto.AdvertiserProfile
import org.example.marketingprofilesapierver.dto.service.*
import org.example.marketingprofilesapierver.repository.AdvertiserProfileInfoRepository
import org.example.marketinguserprofileserver.dto.service.UpdateAdvertiserProfileInfoResult
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AdvertiserProfileInfoService(
    private val advertiserProfileInfoRepository: AdvertiserProfileInfoRepository
) {

    fun saveAdvertiserProfileInfo(domain: AdvertiserProfile): SaveAdvertiserProfileInfoResult {
        return transaction {
            val savedId = advertiserProfileInfoRepository.save(domain)
            SaveAdvertiserProfileInfoResult(savedId = savedId)
        }
    }

    fun getAdvertiserProfileInfoById(advertiserId: UUID): GetAdvertiserProfileInfoResult? {
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

    fun updateAdvertiserProfileInfoById(advertiserId: UUID, domain: AdvertiserProfile): UpdateAdvertiserProfileInfoResult {
        return transaction {
            val updatedCount = advertiserProfileInfoRepository.updateByAdvertiserId(advertiserId, domain)
            UpdateAdvertiserProfileInfoResult(updatedCount = updatedCount)
        }
    }

    fun deleteAdvertiserProfileInfoById(advertiserId: UUID): DeleteAdvertiserProfileInfoResult {
        return transaction {
            val deletedCount = advertiserProfileInfoRepository.deleteByAdvertiserId(advertiserId)
            DeleteAdvertiserProfileInfoResult(deletedCount = deletedCount)
        }
    }
}
