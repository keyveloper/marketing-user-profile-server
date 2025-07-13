package org.example.marketinguserprofileserver.repository

import org.example.marketinguserprofileserver.dto.AdvertiserProfile
import org.example.marketinguserprofileserver.dto.AdvertiserProfileInfoEntity
import org.springframework.stereotype.Repository

@Repository
class AdvertiserProfileInfoRepository {

    fun save(domain: AdvertiserProfile): Long {
        val entity = AdvertiserProfileInfoEntity.new {
            advertiserId = domain.advertiserId
            userProfileDraftId = domain.userProfileDraftId
            serviceInfo = domain.serviceInfo
            locationBrief = domain.locationBrief
            introduction = domain.introduction
        }
        return entity.id.value
    }

    fun findById(targetId: Long): AdvertiserProfileInfoEntity? {
        return AdvertiserProfileInfoEntity.findById(targetId)
    }

    fun updateById(targetId: Long, domain: AdvertiserProfile): Int {
        val entity = AdvertiserProfileInfoEntity.findById(targetId)
        return if (entity != null) {
            entity.advertiserId = domain.advertiserId
            entity.userProfileDraftId = domain.userProfileDraftId
            entity.serviceInfo = domain.serviceInfo
            entity.locationBrief = domain.locationBrief
            entity.introduction = domain.introduction
            1
        } else {
            0
        }
    }

    fun deleteById(targetId: Long): Int {
        val entity = AdvertiserProfileInfoEntity.findById(targetId)
        return if (entity != null) {
            entity.delete()
            1
        } else {
            0
        }
    }
}
