package org.example.marketingprofileapiserver.dto.controller

import java.util.UUID

data class GetInfluencerProfileInfosByIdsApiRequest(
    val influencerIds: List<UUID>
)
