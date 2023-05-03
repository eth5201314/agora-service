package com.agora.services.checkingaccount.application.controller

import com.agora.services.checkingaccount.domain.entities.dto.EstudanteResponseDto
import com.agora.services.checkingaccount.domain.entities.dto.EstudanteRequestDto
import com.agora.services.checkingaccount.domain.entities.Estudante
import com.agora.services.checkingaccount.application.repository.EstudanteRepository
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorResponseDto
import com.agora.services.checkingaccount.resources.lattesidentifier.LattesIdentifierClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/estudante")
class EstudanteController @Autowired constructor(
    val estudanteRepository: EstudanteRepository
) {

    private val codSenha = BCryptPasswordEncoder()
    val logger: Logger = LoggerFactory.getLogger(EstudanteController::class.java)

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid estudante: Estudante): ResponseEntity<Estudante> =
        estudanteRepository.findByEmail(estudante.email)?.let {
            logger.error("O e-mail ${estudante.email} já existe no sistema")
            throw ResponseStatusException(HttpStatus.CONFLICT, "O e-mail ${estudante.email} já existe no sistema")
        } ?: run {
            val senhaCodificada = codSenha.encode(estudante.senha)
            estudante.senha = senhaCodificada

            estudanteRepository.save(estudante)

            logger.info("Cadastro realizado com sucesso")
            ResponseEntity.status(HttpStatus.CREATED).body(estudante)
        }

    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody login: EstudanteRequestDto): ResponseEntity<EstudanteResponseDto> =
        estudanteRepository.findByEmail(login.email)?.takeIf { codSenha.matches(login.senha, it.senha) }
            ?.let {
                logger.info("Login realizado com sucesso")
                ResponseEntity.ok(EstudanteResponseDto(it))
            } ?: run {
            logger.error("O usuário ou senha esta incorreto")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário ou senha esta incorreto")
        }
}




