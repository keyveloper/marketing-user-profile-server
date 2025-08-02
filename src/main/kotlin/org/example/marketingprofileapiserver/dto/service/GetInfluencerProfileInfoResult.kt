package org.example.marketingprofileapiserver.dto.service

import java.util.UUID

data class GetInfluencerProfileInfoResult(
    val id: Long,
    val userProfileDraftId: UUID,
    val influencerName: String,
    val influencerId: UUID,
    val introduction: String?,
    val job: String?,
    val createdAt: Long,
    val updatedAt: Long
)
