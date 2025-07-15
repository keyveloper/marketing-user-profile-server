package org.example.marketingprofilesapierver.dto

import org.example.marketingprofilesapierver.table.InfluencerProfileInfosTable
import org.jetbrains.exposed.dao.id.EntityID

class InfluencerProfileInfoEntity(id: EntityID<Long>): BaseDateEntity(id, InfluencerProfileInfosTable) {
    companion object: BaseDateEntityClass<InfluencerProfileInfoEntity>(InfluencerProfileInfosTable)

    var userProfileDraftId by InfluencerProfileInfosTable.userProfileDraftId
    var influencerId by InfluencerProfileInfosTable.influencerId
    var introduction by InfluencerProfileInfosTable.introduction
    var job by InfluencerProfileInfosTable.job
}
