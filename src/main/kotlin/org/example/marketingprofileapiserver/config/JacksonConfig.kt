package org.example.marketingprofileapiserver.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper().apply {
            // HttpStatus를 "CREATED"로 직렬화 (toString() 대신 name() 사용)
            configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false)
        }
    }
}
