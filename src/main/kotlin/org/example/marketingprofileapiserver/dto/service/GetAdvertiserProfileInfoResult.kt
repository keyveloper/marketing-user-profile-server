package org.example.marketingprofileapiserver.dto.service

import java.util.UUID

data class GetAdvertiserProfileInfoResult(
    val id: Long,
    val advertiserId: UUID,
    val userProfileDraftId: UUID,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?,
    val createdAt: Long,
    val updatedAt: Long
)
