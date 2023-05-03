package com.agora.services.checkingaccount.application.config

import com.agora.services.checkingaccount.application.mapper.MateriaConverter
import com.agora.services.checkingaccount.domain.entities.Materia
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException

@Configuration
class JacksonConfiguration {
    @Autowired
    private val materiaConverter: MateriaConverter? = null

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(
            SimpleModule().addDeserializer(
                Materia::class.java,
                object : StdDeserializer<Materia>(Materia::class.java) {
                    @Throws(IOException::class)
                    override fun deserialize(parser: JsonParser?, context: DeserializationContext?): Materia? {
                        val id = parser?.text ?: return null
                        return materiaConverter?.apply(id)
                    }
                })
        )
        return objectMapper
    }
}
