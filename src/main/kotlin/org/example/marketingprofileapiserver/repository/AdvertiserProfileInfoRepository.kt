package org.example.marketingprofileapiserver.repository

import org.example.marketingprofileapiserver.dto.UpdateAdvertiserProfile
import org.example.marketingprofileapiserver.dto.AdvertiserProfileInfoEntity
import org.example.marketingprofileapiserver.dto.service.AdvertiserProfileInfo
import org.example.marketingprofileapiserver.exception.DuplicatedAdvertiserProfileInfoEntityException
import org.example.marketingprofileapiserver.table.AdvertiserProfileInfosTable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AdvertiserProfileInfoRepository {

    fun save(domain: UpdateAdvertiserProfile): Long {
        if (existsByAdvertiserId(domain.advertiserId)) {
            throw DuplicatedAdvertiserProfileInfoEntityException(domain.advertiserId)
        }

        // Create new entity
        val entity = AdvertiserProfileInfoEntity.new {
            advertiserId = domain.advertiserId
            advertiserName = domain.advertiserName
            userProfileDraftId = domain.userProfileDraftId
            serviceInfo = domain.serviceInfo
            locationBrief = domain.locationBrief
            introduction = domain.introduction
        }
        return entity.id.value
    }

    fun findById(targetId: Long): AdvertiserProfileInfo? {
        val entity = AdvertiserProfileInfoEntity.findById(targetId) ?: return null
        return AdvertiserProfileInfo.of(entity)
    }

    fun findByAdvertiserId(advertiserId: UUID): AdvertiserProfileInfo? {
        val entity = AdvertiserProfileInfoEntity.find { AdvertiserProfileInfosTable.advertiserId eq advertiserId }.firstOrNull() ?: return null
        return AdvertiserProfileInfo.of(entity)
    }

    fun findAllByAdvertiserIds(advertiserIds: List<UUID>): List<AdvertiserProfileInfo> {
        if (advertiserIds.isEmpty()) {
            return emptyList()
        }
        return AdvertiserProfileInfoEntity.find {
            AdvertiserProfileInfosTable.advertiserId inList advertiserIds
        }.map { AdvertiserProfileInfo.of(it) }
    }

    fun existsByAdvertiserId(advertiserId: UUID): Boolean {
        return AdvertiserProfileInfoEntity.find { AdvertiserProfileInfosTable.advertiserId eq advertiserId }.firstOrNull() != null
    }

    fun updateById(targetId: Long, domain: UpdateAdvertiserProfile): Int {
        val entity = AdvertiserProfileInfoEntity.findById(targetId)
        return if (entity != null) {
            entity.advertiserId = domain.advertiserId
            entity.advertiserName = domain.advertiserName
            entity.userProfileDraftId = domain.userProfileDraftId
            entity.serviceInfo = domain.serviceInfo
            entity.locationBrief = domain.locationBrief
            entity.introduction = domain.introduction
            1
        } else {
            0
        }
    }

    fun updateByAdvertiserId(advertiserId: UUID, domain: UpdateAdvertiserProfile): Int {
        val entity = AdvertiserProfileInfoEntity.find { AdvertiserProfileInfosTable.advertiserId eq advertiserId }.firstOrNull()
        return if (entity != null) {
            entity.advertiserId = domain.advertiserId
            entity.advertiserName = domain.advertiserName
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
        val entity = AdvertiserProfileInfoEntity.find { AdvertiserProfileInfosTable.advertiserId eq advertiserId }.firstOrNull()
        return if (entity != null) {
            entity.delete()
            1
        } else {
            0
        }
    }
}
