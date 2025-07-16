package org.example.marketingprofilesapierver.exception

import org.example.marketingprofilesapierver.enums.MSAServiceErrorCode
import org.springframework.http.HttpStatus
import java.util.UUID

class DuplicatedAdvertiserProfileInfoEntityException(
    advertiserId: UUID,
    logics: String = "AdvertiserProfileInfoRepository.save"
) : MSAServerException(
    httpStatus = HttpStatus.CONFLICT,
    msaServiceErrorCode = MSAServiceErrorCode.DUPLICATED_ADVERTISER_PROFILE,
    logics = logics,
    message = "Advertiser profile already exists with advertiserId: $advertiserId"
)