package org.example.marketinguserprofileserver.dto.controller

data class SaveInfluencerProfileInfoRequest(
    val influencerId: String,
    val introduction: String?,
    val job: String?
)
