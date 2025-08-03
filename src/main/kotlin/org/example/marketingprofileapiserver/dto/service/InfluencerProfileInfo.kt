package org.example.marketingprofileapiserver.dto.service

import org.example.marketingprofileapiserver.dto.InfluencerProfileInfoEntity
import java.util.UUID

data class InfluencerProfileInfo(
    val id: Long,
    val userProfileDraftId: UUID,
    val influencerName: String,
    val influencerId: UUID,
    val introduction: String?,
    val job: String?,
    val createdAt: Long,
    val updatedAt: Long
) {
    companion object {
        fun of(entity: InfluencerProfileInfoEntity): InfluencerProfileInfo {
            return InfluencerProfileInfo(
                id = entity.id.value,
                userProfileDraftId = entity.userProfileDraftId,
                influencerName = entity.influencerName,
                influencerId = entity.influencerId,
                introduction = entity.introduction,
                job = entity.job,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }
}
