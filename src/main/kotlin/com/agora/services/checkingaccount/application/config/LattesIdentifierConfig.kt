package com.agora.services.checkingaccount.application.config

import com.agora.services.checkingaccount.resources.lattesidentifier.LattesIdentifierClient
import com.agora.services.checkingaccount.resources.lattesidentifier.LattesIdentifierService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class LattesIdentifierConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
    @Bean
    fun lattesIdentifierService(restTemplate: RestTemplate): LattesIdentifierService {
        val client = LattesIdentifierClient(restTemplate)
        return LattesIdentifierService(client)
    }
}