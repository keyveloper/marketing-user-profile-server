package org.example.marketinguserprofileserver.dto.controller

import java.util.UUID

data class UpdateInfluencerProfileInfoRequest(
    val userProfileDraftId: UUID,
    val influencerId: UUID,
    val introduction: String?,
    val job: String?
)
