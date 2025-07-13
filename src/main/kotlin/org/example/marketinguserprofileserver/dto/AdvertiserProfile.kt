package org.example.marketinguserprofileserver.dto

import java.util.UUID

data class AdvertiserProfile(
    val id: Long? = null,
    val advertiserId: UUID,
    val userProfileDraftId: UUID,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)
