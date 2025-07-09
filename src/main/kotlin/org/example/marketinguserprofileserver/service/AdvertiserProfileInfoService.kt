package org.example.marketinguserprofileserver.service

import org.example.marketinguserprofileserver.dto.AdvertiserProfile
import org.example.marketinguserprofileserver.dto.service.*
import org.example.marketinguserprofileserver.repository.AdvertiserProfileInfoRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

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

    fun getAdvertiserProfileInfoById(targetId: Long): GetAdvertiserProfileInfoResult? {
        return transaction {
            val entity = advertiserProfileInfoRepository.findById(targetId) ?: return@transaction null
            GetAdvertiserProfileInfoResult(
                id = entity.id.value,
                advertiserId = entity.advertiserId,
                serviceInfo = entity.serviceInfo,
                locationBrief = entity.locationBrief,
                introduction = entity.introduction,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    fun updateAdvertiserProfileInfoById(targetId: Long, domain: AdvertiserProfile): UpdateAdvertiserProfileInfoResult {
        return transaction {
            val updatedCount = advertiserProfileInfoRepository.updateById(targetId, domain)
            UpdateAdvertiserProfileInfoResult(updatedCount = updatedCount)
        }
    }

    fun deleteAdvertiserProfileInfoById(targetId: Long): DeleteAdvertiserProfileInfoResult {
        return transaction {
            val deletedCount = advertiserProfileInfoRepository.deleteById(targetId)
            DeleteAdvertiserProfileInfoResult(deletedCount = deletedCount)
        }
    }
}
