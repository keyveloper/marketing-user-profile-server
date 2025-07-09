package org.example.marketinguserprofileserver.dto.service

data class GetInfluencerProfileInfoResult(
    val id: Long,
    val influencerId: String,
    val introduction: String?,
    val job: String?,
    val createdAt: Long,
    val updatedAt: Long
)
