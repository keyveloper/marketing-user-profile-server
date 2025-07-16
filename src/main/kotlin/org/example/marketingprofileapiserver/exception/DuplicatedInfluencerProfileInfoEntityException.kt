package org.example.marketingprofileapiserver.exception

import org.example.marketingprofileapiserver.enums.MSAServiceErrorCode
import org.springframework.http.HttpStatus
import java.util.UUID

class DuplicatedInfluencerProfileInfoEntityException(
    influencerId: UUID,
    logics: String = "InfluencerProfileInfoRepository.save"
) : MSAServerException(
    httpStatus = HttpStatus.CONFLICT,
    msaServiceErrorCode = MSAServiceErrorCode.DUPLICATED_INFLUENCER_PROFILE,
    logics = logics,
    message = "Influencer profile already exists with influencerId: $influencerId"
)