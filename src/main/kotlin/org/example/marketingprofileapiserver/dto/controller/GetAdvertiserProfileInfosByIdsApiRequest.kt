package org.example.marketingprofileapiserver.dto.controller

import java.util.UUID

data class GetAdvertiserProfileInfosByIdsApiRequest(
    val advertiserIds: List<UUID>
)
