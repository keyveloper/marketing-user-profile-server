package org.example.marketinguserprofileserver.table

import org.jetbrains.exposed.sql.Column

object AdvertiserProfileInfosTable: BaseDateLongIdTable("advertiser_profile_infos") {
    val advertiserId: Column<String> = varchar("advertiser_id", 255).uniqueIndex()
    val serviceInfo: Column<String> = varchar("service_info", 255)
    val locationBrief: Column<String> = varchar("location_brief", 255)
    val introduction: Column<String?> = varchar("introduction", 255).nullable()
}