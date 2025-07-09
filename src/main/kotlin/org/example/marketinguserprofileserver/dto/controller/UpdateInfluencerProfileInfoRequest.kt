package org.example.marketinguserprofileserver.dto.controller

data class UpdateInfluencerProfileInfoRequest(
    val influencerId: String,
    val introduction: String?,
    val job: String?
)
