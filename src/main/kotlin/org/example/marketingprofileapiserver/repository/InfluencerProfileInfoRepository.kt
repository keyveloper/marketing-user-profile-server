package org.example.marketingprofileapiserver.repository

import org.example.marketingprofileapiserver.dto.UpdateInfluencerProfile
import org.example.marketingprofileapiserver.dto.InfluencerProfileInfoEntity
import org.example.marketingprofileapiserver.dto.service.InfluencerProfileInfo
import org.example.marketingprofileapiserver.exception.DuplicatedInfluencerProfileInfoEntityException
import org.example.marketingprofileapiserver.table.InfluencerProfileInfosTable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class InfluencerProfileInfoRepository {

    fun save(domain: UpdateInfluencerProfile): Long {
        if (existsByInfluencerId(domain.influencerId)) {
            throw DuplicatedInfluencerProfileInfoEntityException(domain.influencerId)
        }

        // Create new entity
        val entity = InfluencerProfileInfoEntity.new {
            userProfileDraftId = domain.userProfileDraftId
            influencerName = domain.influencerName
            influencerId = domain.influencerId
            introduction = domain.introduction
            job = domain.job
        }
        return entity.id.value
    }

    fun findById(targetId: Long): InfluencerProfileInfo? {
        val entity = InfluencerProfileInfoEntity.findById(targetId) ?: return null
        return InfluencerProfileInfo.of(entity)
    }

    fun findByInfluencerId(influencerId: UUID): InfluencerProfileInfo? {
        val entity = InfluencerProfileInfoEntity.find { InfluencerProfileInfosTable.influencerId eq influencerId }.firstOrNull() ?: return null
        return InfluencerProfileInfo.of(entity)
    }

    fun findAllByInfluencerIds(influencerIds: List<UUID>): List<InfluencerProfileInfo> {
        if (influencerIds.isEmpty()) {
            return emptyList()
        }
        return InfluencerProfileInfoEntity.find { InfluencerProfileInfosTable.influencerId inList influencerIds }
            .map { InfluencerProfileInfo.of(it) }
    }

    fun existsByInfluencerId(influencerId: UUID): Boolean {
        return InfluencerProfileInfoEntity.find { InfluencerProfileInfosTable.influencerId eq influencerId }.firstOrNull() != null
    }

    fun updateById(targetId: Long, domain: UpdateInfluencerProfile): Int {
        val entity = InfluencerProfileInfoEntity.findById(targetId)
        return if (entity != null) {
            entity.userProfileDraftId = domain.userProfileDraftId
            entity.influencerName = domain.influencerName
            entity.influencerId = domain.influencerId
            entity.introduction = domain.introduction
            entity.job = domain.job
            1
        } else {
            0
        }
    }

    fun updateByInfluencerId(influencerId: UUID, domain: UpdateInfluencerProfile): Int {
        val entity = InfluencerProfileInfoEntity.find { InfluencerProfileInfosTable.influencerId eq influencerId }.firstOrNull()
        return if (entity != null) {
            entity.userProfileDraftId = domain.userProfileDraftId
            entity.influencerName = domain.influencerName
            entity.influencerId = domain.influencerId
            entity.introduction = domain.introduction
            entity.job = domain.job
            1
        } else {
            0
        }
    }

    fun deleteById(targetId: Long): Int {
        val entity = InfluencerProfileInfoEntity.findById(targetId)
        return if (entity != null) {
            entity.delete()
            1
        } else {
            0
        }
    }

    fun deleteByInfluencerId(influencerId: UUID): Int {
        val entity = InfluencerProfileInfoEntity.find { InfluencerProfileInfosTable.influencerId eq influencerId }.firstOrNull()
        return if (entity != null) {
            entity.delete()
            1
        } else {
            0
        }
    }
}
