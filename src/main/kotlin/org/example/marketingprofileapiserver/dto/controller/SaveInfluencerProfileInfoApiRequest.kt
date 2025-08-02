package org.example.marketingprofileapiserver.dto.controller

import java.util.UUID

data class SaveInfluencerProfileInfoApiRequest(
    val userProfileDraftId: UUID,
    val influencerName: String,
    val influencerId: UUID,
    val introduction: String?,
    val job: String?
)
