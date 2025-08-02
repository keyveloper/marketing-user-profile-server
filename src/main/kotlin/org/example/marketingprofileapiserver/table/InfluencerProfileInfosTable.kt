package org.example.marketingprofileapiserver.table

import org.jetbrains.exposed.sql.Column
import java.util.UUID

object InfluencerProfileInfosTable: BaseDateLongIdTable("influencer_profile_infos") {
    val userProfileDraftId: Column<UUID> = uuid("user_profile_draft_id")
    val influencerName: Column<String> = varchar("influencer_name", 255)
    val influencerId: Column<UUID> = uuid("influencer_id").uniqueIndex()
    val introduction: Column<String?> = varchar("introduction", 255).nullable()
    val job: Column<String?> = varchar("job", 255).nullable()
}
