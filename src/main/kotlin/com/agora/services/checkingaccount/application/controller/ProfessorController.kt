package com.agora.services.checkingaccount.application.controller

import com.agora.services.checkingaccount.application.repository.ProfessorRepository
import com.agora.services.checkingaccount.domain.entities.Professor
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorRequestDto
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorResponseDto
import com.agora.services.checkingaccount.resources.lattesidentifier.LattesIdentifierClient
import com.agora.services.checkingaccount.resources.lattesidentifier.LattesIdentifierService
import com.agora.services.checkingaccount.resources.lattesidentifier.entities.Lattes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/professor")
class ProfessorController @Autowired constructor(
    val professorRepository: ProfessorRepository,
    val lattesService: LattesIdentifierService
) {

    private val codSenha = BCryptPasswordEncoder()
    val logger: Logger = LoggerFactory.getLogger(ProfessorController::class.java)

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid professor: Professor): ResponseEntity<Professor> =
        professorRepository.findByEmail(professor.email)?.let {
            logger.error("O e-mail ${professor.email} já existe no sistema")
            throw ResponseStatusException(HttpStatus.CONFLICT, "O e-mail ${professor.email} já existe no sistema")
        } ?: run {
            professorRepository.findByIdLattes(professor.idLattes)?.let {
                logger.error("O ID Lattes ${professor.idLattes} já existe no sistema")
                throw ResponseStatusException(HttpStatus.CONFLICT, "O ID Lattes ${professor.idLattes} já existe no sistema")
            }

            lattesService.lattesValid(Lattes(professor.idLattes))

            val senhaCodificada = codSenha.encode(professor.senha)
            professor.senha = senhaCodificada

            val savedProfessor = professorRepository.save(professor)

            logger.info("Cadastro realizado com sucesso")
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor)
        }


    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody login: ProfessorRequestDto): ResponseEntity<ProfessorResponseDto> =
        professorRepository.findByEmail(login.email)?.takeIf { codSenha.matches(login.senha, it.senha) }
            ?.let {
                logger.info("Login realizado com sucesso")
                ResponseEntity.ok(ProfessorResponseDto(it))
            } ?: run {
            logger.error("O usuário ou senha esta incorreto")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário ou senha esta incorreto")
        }
}