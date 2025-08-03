package org.example.marketingprofileapiserver.dto.service

data class GetAdvertiserProfileInfosByIdsResult(
    val advertiserProfileInfos: List<AdvertiserProfileInfo>
) {
    companion object {
        fun of(advertiserProfileInfos: List<AdvertiserProfileInfo>): GetAdvertiserProfileInfosByIdsResult {
            return GetAdvertiserProfileInfosByIdsResult(
                advertiserProfileInfos
            )
        }
    }
}

