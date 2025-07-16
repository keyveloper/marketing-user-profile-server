package org.example.marketingprofileapiserver.dto.controller

import java.util.UUID

data class UpdateAdvertiserProfileInfoApiRequest(
    val advertiserId: UUID,
    val userProfileDraftId: UUID,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?
)
