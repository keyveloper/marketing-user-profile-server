package org.example.marketingprofileapiserver.repository

import org.example.marketingprofileapiserver.dto.InfluencerProfile
import org.example.marketingprofileapiserver.dto.InfluencerProfileInfoEntity
import org.example.marketingprofileapiserver.exception.DuplicatedInfluencerProfileInfoEntityException
import org.example.marketingprofileapiserver.table.InfluencerProfileInfosTable
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class InfluencerProfileInfoRepository {

    fun save(domain: InfluencerProfile): Long {
        val existingEntity = findByInfluencerId(domain.influencerId)

        if (existingEntity != null) {
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

    fun findById(targetId: Long): InfluencerProfileInfoEntity? {
        return InfluencerProfileInfoEntity.findById(targetId)
    }

    fun findByInfluencerId(influencerId: UUID): InfluencerProfileInfoEntity? {
        return InfluencerProfileInfoEntity.find { InfluencerProfileInfosTable.influencerId eq influencerId }.firstOrNull()
    }

    fun updateById(targetId: Long, domain: InfluencerProfile): Int {
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

    fun updateByInfluencerId(influencerId: UUID, domain: InfluencerProfile): Int {
        val entity = findByInfluencerId(influencerId)
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
        val entity = findByInfluencerId(influencerId)
        return if (entity != null) {
            entity.delete()
            1
        } else {
            0
        }
    }
}
