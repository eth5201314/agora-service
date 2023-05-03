package com.agora.services.checkingaccount.domain.entities.services

import com.agora.services.checkingaccount.application.repository.EstudanteRepository
import com.agora.services.checkingaccount.domain.entities.Estudante
import com.agora.services.checkingaccount.domain.entities.dto.EstudanteRequestDto
import com.agora.services.checkingaccount.domain.entities.dto.EstudanteResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EstudanteService @Autowired constructor(
    private val estudanteRepository: EstudanteRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(EstudanteService::class.java)
    private val codSenha = BCryptPasswordEncoder()

    fun createUser(user: Estudante): ResponseEntity<Estudante> =
        estudanteRepository.findByEmail(user.email)?.let {
            logger.error("O e-mail ${user.email} já existe no sistema")
            throw ResponseStatusException(HttpStatus.CONFLICT, "O e-mail ${user.email} já existe no sistema")
        } ?: run {
            val senhaCodificada = codSenha.encode(user.senha)
            user.senha = senhaCodificada

            estudanteRepository.save(user)

            logger.info("Cadastro realizado com sucesso")
            ResponseEntity.status(HttpStatus.CREATED).body(user)
        }

    fun login(user: EstudanteRequestDto): ResponseEntity<EstudanteResponseDto> =
        estudanteRepository.findByEmail(user.email)?.takeIf { codSenha.matches(user.senha, it.senha) }
            ?.let {
                logger.info("Login realizado com sucesso")
                ResponseEntity.ok(EstudanteResponseDto(it))
            } ?: run {
            logger.error("O usuário ou senha esta incorreto")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário ou senha esta incorreto")
        }
}