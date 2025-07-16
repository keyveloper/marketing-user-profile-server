package org.example.marketingprofileapiserver.repository

import org.example.marketingprofileapiserver.dto.AdvertiserProfile
import org.example.marketingprofileapiserver.dto.AdvertiserProfileInfoEntity
import org.example.marketingprofileapiserver.exception.DuplicatedAdvertiserProfileInfoEntityException
import org.example.marketingprofileapiserver.table.AdvertiserProfileInfosTable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AdvertiserProfileInfoRepository {

    fun save(domain: AdvertiserProfile): Long {
        val existingEntity = findByAdvertiserId(domain.advertiserId)

        if (existingEntity != null) {
            throw DuplicatedAdvertiserProfileInfoEntityException(domain.advertiserId)
        }

        // Create new entity
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

    fun findByAdvertiserId(advertiserId: UUID): AdvertiserProfileInfoEntity? {
        return AdvertiserProfileInfoEntity.find { AdvertiserProfileInfosTable.advertiserId eq advertiserId }.firstOrNull()
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

    fun updateByAdvertiserId(advertiserId: UUID, domain: AdvertiserProfile): Int {
        val entity = findByAdvertiserId(advertiserId)
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

    fun deleteByAdvertiserId(advertiserId: UUID): Int {
        val entity = findByAdvertiserId(advertiserId)
        return if (entity != null) {
            entity.delete()
            1
        } else {
            0
        }
    }
}
