package org.example.marketinguserprofileserver.dto.service

data class GetAdvertiserProfileInfoResult(
    val id: Long,
    val advertiserId: String,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?,
    val createdAt: Long,
    val updatedAt: Long
)
