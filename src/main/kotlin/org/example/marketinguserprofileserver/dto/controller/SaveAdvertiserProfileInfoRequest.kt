package org.example.marketinguserprofileserver.dto.controller

import java.util.UUID

data class SaveAdvertiserProfileInfoRequest(
    val advertiserId: UUID,
    val userProfileDraftId: UUID,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?
)
