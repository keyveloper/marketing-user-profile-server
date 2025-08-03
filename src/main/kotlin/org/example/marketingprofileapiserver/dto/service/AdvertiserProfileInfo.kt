package org.example.marketingprofileapiserver.dto.service

import org.example.marketingprofileapiserver.dto.AdvertiserProfileInfoEntity
import java.util.UUID

data class AdvertiserProfileInfo(
    val id: Long,
    val advertiserId: UUID,
    val advertiserName: String,
    val userProfileDraftId: UUID,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?,
    val createdAt: Long,
    val updatedAt: Long
) {
    companion object {
        fun of(entity: AdvertiserProfileInfoEntity): AdvertiserProfileInfo {
            return AdvertiserProfileInfo(
                id = entity.id.value,
                advertiserId = entity.advertiserId,
                advertiserName = entity.advertiserName,
                userProfileDraftId = entity.userProfileDraftId,
                serviceInfo = entity.serviceInfo,
                locationBrief = entity.locationBrief,
                introduction = entity.introduction,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }
}