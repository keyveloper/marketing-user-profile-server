package org.example.marketingprofileapiserver.dto

import java.util.UUID

data class UpdateAdvertiserProfile(
    val id: Long? = null,
    val advertiserId: UUID,
    val advertiserName: String,
    val userProfileDraftId: UUID,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)
