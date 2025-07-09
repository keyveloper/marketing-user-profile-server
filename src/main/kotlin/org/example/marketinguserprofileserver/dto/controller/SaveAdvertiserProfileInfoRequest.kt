package org.example.marketinguserprofileserver.dto.controller

data class SaveAdvertiserProfileInfoRequest(
    val advertiserId: String,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?
)
