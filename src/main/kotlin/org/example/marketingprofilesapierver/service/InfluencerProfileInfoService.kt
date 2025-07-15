package org.example.marketingprofilesapierver.service

import org.example.marketingprofilesapierver.dto.InfluencerProfile
import org.example.marketingprofilesapierver.dto.service.DeleteInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.dto.service.GetInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.dto.service.UpdateInfluencerProfileInfoResult
import org.example.marketingprofilesapierver.repository.InfluencerProfileInfoRepository
import org.example.marketinguserprofileserver.dto.service.SaveInfluencerProfileInfoResult
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class InfluencerProfileInfoService(
    private val influencerProfileInfoRepository: InfluencerProfileInfoRepository
) {

    fun saveInfluencerProfileInfo(domain: InfluencerProfile): SaveInfluencerProfileInfoResult {
        return transaction {
            val savedId = influencerProfileInfoRepository.save(domain)
            SaveInfluencerProfileInfoResult(savedId = savedId)
        }
    }

    fun getInfluencerProfileInfoById(influencerId: UUID): GetInfluencerProfileInfoResult? {
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

    fun updateInfluencerProfileInfoById(influencerId: UUID, domain: InfluencerProfile): UpdateInfluencerProfileInfoResult {
        return transaction {
            val updatedCount = influencerProfileInfoRepository.updateByInfluencerId(influencerId, domain)
            UpdateInfluencerProfileInfoResult(updatedCount = updatedCount)
        }
    }

    fun deleteInfluencerProfileInfoById(influencerId: UUID): DeleteInfluencerProfileInfoResult {
        return transaction {
            val deletedCount = influencerProfileInfoRepository.deleteByInfluencerId(influencerId)
            DeleteInfluencerProfileInfoResult(deletedCount = deletedCount)
        }
    }
}
