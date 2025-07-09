package org.example.marketinguserprofileserver.dto

data class AdvertiserProfile(
    val id: Long? = null,
    val advertiserId: String,
    val serviceInfo: String,
    val locationBrief: String,
    val introduction: String?,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)
