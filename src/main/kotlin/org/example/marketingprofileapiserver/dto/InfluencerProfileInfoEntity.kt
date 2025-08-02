package org.example.marketingprofileapiserver.dto

import org.example.marketingprofileapiserver.table.InfluencerProfileInfosTable
import org.jetbrains.exposed.dao.id.EntityID

class InfluencerProfileInfoEntity(id: EntityID<Long>): BaseDateEntity(id, InfluencerProfileInfosTable) {
    companion object: BaseDateEntityClass<InfluencerProfileInfoEntity>(InfluencerProfileInfosTable)

    var userProfileDraftId by InfluencerProfileInfosTable.userProfileDraftId
    var influencerName by InfluencerProfileInfosTable.influencerName
    var influencerId by InfluencerProfileInfosTable.influencerId
    var introduction by InfluencerProfileInfosTable.introduction
    var job by InfluencerProfileInfosTable.job
}
