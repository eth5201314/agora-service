package com.agora.services.checkingaccount.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class ConfiguracaoValidacoes : RepositoryRestConfigurer {

    @Bean
    fun validator(): Validator? {
        return LocalValidatorFactoryBean()
    }

    override fun configureValidatingRepositoryEventListener(validatingListener: ValidatingRepositoryEventListener) {
        validatingListener.addValidator("beforeCreate", validator())
        validatingListener.addValidator("beforeSave", validator())
    }

}