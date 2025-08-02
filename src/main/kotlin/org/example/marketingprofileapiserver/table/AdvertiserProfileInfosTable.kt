package org.example.marketingprofileapiserver.table

import org.jetbrains.exposed.sql.Column
import java.util.UUID

object AdvertiserProfileInfosTable: BaseDateLongIdTable("advertiser_profile_infos") {
    val advertiserId: Column<UUID> = uuid("advertiser_id").uniqueIndex()
    val advertiserName: Column<String> = varchar("advertiser_name", 255)
    val userProfileDraftId: Column<UUID> = uuid("user_profile_draft_id")
    val serviceInfo: Column<String> = varchar("service_info", 255)
    val locationBrief: Column<String> = varchar("location_brief", 255)
    val introduction: Column<String?> = varchar("introduction", 255).nullable()
}