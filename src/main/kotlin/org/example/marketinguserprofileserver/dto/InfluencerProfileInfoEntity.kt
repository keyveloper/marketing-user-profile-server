package org.example.marketinguserprofileserver.dto

import org.example.marketinguserprofileserver.table.InfluencerProfileInfosTable
import org.jetbrains.exposed.dao.id.EntityID

class InfluencerProfileInfoEntity(id: EntityID<Long>): BaseDateEntity(id, InfluencerProfileInfosTable) {
    companion object: BaseDateEntityClass<InfluencerProfileInfoEntity>(InfluencerProfileInfosTable)

    var influencerId by InfluencerProfileInfosTable.influencerId
    var introduction by InfluencerProfileInfosTable.introduction
    var job by InfluencerProfileInfosTable.job
}
