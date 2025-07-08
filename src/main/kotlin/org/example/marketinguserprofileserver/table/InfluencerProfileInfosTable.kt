package org.example.marketinguserprofileserver.table

import org.jetbrains.exposed.sql.Column

object InfluencerProfileInfosTable: BaseDateLongIdTable("influencer_profile_infos") {
    val influencerId: Column<String> = varchar("influencer_id", 255).uniqueIndex()
    val introduction: Column<String?> = varchar("introduction", 255).nullable()
    val job: Column<String?> = varchar("job", 255).nullable()
}
