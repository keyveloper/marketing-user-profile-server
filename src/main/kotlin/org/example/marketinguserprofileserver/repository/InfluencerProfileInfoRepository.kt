package org.example.marketinguserprofileserver.repository

import org.example.marketinguserprofileserver.dto.InfluencerProfile
import org.example.marketinguserprofileserver.dto.InfluencerProfileInfoEntity
import org.springframework.stereotype.Repository

@Repository
class InfluencerProfileInfoRepository {

    fun save(domain: InfluencerProfile): Long {
        val entity = InfluencerProfileInfoEntity.new {
            influencerId = domain.influencerId
            introduction = domain.introduction
            job = domain.job
        }
        return entity.id.value
    }

    fun findById(targetId: Long): InfluencerProfileInfoEntity? {
        return InfluencerProfileInfoEntity.findById(targetId)
    }

    fun updateById(targetId: Long, domain: InfluencerProfile): Int {
        val entity = InfluencerProfileInfoEntity.findById(targetId)
        return if (entity != null) {
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
}
