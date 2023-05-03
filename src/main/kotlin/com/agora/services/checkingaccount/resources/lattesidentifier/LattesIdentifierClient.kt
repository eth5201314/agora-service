package com.agora.services.checkingaccount.resources.lattesidentifier

import com.agora.services.checkingaccount.resources.lattesidentifier.entities.Lattes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

@Component
class LattesIdentifierClient(
    private val restTemplate: RestTemplate
) {

    val logger: Logger = LoggerFactory.getLogger(LattesIdentifierClient::class.java)
    private val apiUrl = "http://localhost:5000/lattes/"

    fun lattesValid(lattes: Lattes): Lattes {
        return try {
            restTemplate.getForEntity("$apiUrl${lattes.idLattes}", String::class.java).let {
                if (it.statusCode != HttpStatus.OK) {
                    throw ResponseStatusException(HttpStatus.NOT_FOUND, "O id Lattes ${lattes.idLattes} é inválido")
                }
                logger.info("Lattes ${lattes.idLattes} validado com sucesso")
                lattes
            }
        } catch (ex: Exception) {
            logger.error("Erro ao validar o Lattes ${lattes.idLattes}")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "O id Lattes ${lattes.idLattes} é inválido")
        }
    }
}