package org.example.marketingprofilesapierver.dto.controller

import java.util.UUID

data class SaveInfluencerProfileInfoApiRequest(
    val userProfileDraftId: UUID,
    val influencerId: UUID,
    val introduction: String?,
    val job: String?
)
