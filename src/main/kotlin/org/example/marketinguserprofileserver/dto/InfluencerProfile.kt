package org.example.marketinguserprofileserver.dto

data class InfluencerProfile(
    val id: Long? = null,
    val influencerId: String,
    val introduction: String?,
    val job: String?,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)
