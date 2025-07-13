package org.example.marketinguserprofileserver.dto

import org.example.marketinguserprofileserver.table.AdvertiserProfileInfosTable
import org.jetbrains.exposed.dao.id.EntityID

class AdvertiserProfileInfoEntity(id: EntityID<Long>): BaseDateEntity(id, AdvertiserProfileInfosTable) {
    companion object: BaseDateEntityClass<AdvertiserProfileInfoEntity>(AdvertiserProfileInfosTable)

    var advertiserId by AdvertiserProfileInfosTable.advertiserId
    var userProfileDraftId by AdvertiserProfileInfosTable.userProfileDraftId
    var serviceInfo by AdvertiserProfileInfosTable.serviceInfo
    var locationBrief by AdvertiserProfileInfosTable.locationBrief
    var introduction by AdvertiserProfileInfosTable.introduction
}
