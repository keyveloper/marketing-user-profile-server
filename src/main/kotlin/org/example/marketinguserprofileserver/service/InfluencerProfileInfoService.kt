package org.example.marketinguserprofileserver.service

import org.example.marketinguserprofileserver.dto.InfluencerProfile
import org.example.marketinguserprofileserver.dto.service.*
import org.example.marketinguserprofileserver.repository.InfluencerProfileInfoRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

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

    fun getInfluencerProfileInfoById(targetId: Long): GetInfluencerProfileInfoResult? {
        return transaction {
            val entity = influencerProfileInfoRepository.findById(targetId) ?: return@transaction null
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

    fun updateInfluencerProfileInfoById(targetId: Long, domain: InfluencerProfile): UpdateInfluencerProfileInfoResult {
        return transaction {
            val updatedCount = influencerProfileInfoRepository.updateById(targetId, domain)
            UpdateInfluencerProfileInfoResult(updatedCount = updatedCount)
        }
    }

    fun deleteInfluencerProfileInfoById(targetId: Long): DeleteInfluencerProfileInfoResult {
        return transaction {
            val deletedCount = influencerProfileInfoRepository.deleteById(targetId)
            DeleteInfluencerProfileInfoResult(deletedCount = deletedCount)
        }
    }
}
