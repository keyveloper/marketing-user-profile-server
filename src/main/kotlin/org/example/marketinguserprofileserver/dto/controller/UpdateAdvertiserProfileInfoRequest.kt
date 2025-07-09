package org.example.marketinguserprofileserver.dto.controller

data class UpdateAdvertiserProfileInfoRequest(
    val advertiserId: String,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?
)
