package org.example.marketingprofileapiserver.dto

import java.util.UUID

data class UpdateInfluencerProfile(
    val id: Long? = null,
    val userProfileDraftId: UUID,
    val influencerName: String,
    val influencerId: UUID,
    val introduction: String?,
    val job: String?,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)
