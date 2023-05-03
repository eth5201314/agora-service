package com.agora.services.checkingaccount.domain.services

import com.agora.services.checkingaccount.application.repository.ProfessorRepository
import com.agora.services.checkingaccount.domain.entities.Professor
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorRequestDto
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorResponseDto
import com.agora.services.checkingaccount.resources.lattesidentifier.LattesIdentifierService
import com.agora.services.checkingaccount.resources.lattesidentifier.entities.Lattes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProfessorService @Autowired constructor(
    private val professorRepository: ProfessorRepository,
    private val lattesService: LattesIdentifierService
) {
    private val logger: Logger = LoggerFactory.getLogger(ProfessorService::class.java)
    private val codSenha = BCryptPasswordEncoder()
    fun createUser(user: Professor): ResponseEntity<Professor> =
        professorRepository.findByEmail(user.email)?.let {
            logger.error("O e-mail ${user.email} já existe no sistema")
            throw ResponseStatusException(HttpStatus.CONFLICT, "O e-mail ${user.email} já existe no sistema")
        } ?: run {
            professorRepository.findByIdLattes(user.idLattes)?.let {
                logger.error("O ID Lattes ${user.idLattes} já existe no sistema")
                throw ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O ID Lattes ${user.idLattes} já existe no sistema"
                )
            }

            lattesService.lattesValid(Lattes(user.idLattes))

            val senhaCodificada = codSenha.encode(user.senha)
            user.senha = senhaCodificada

            val savedProfessor = professorRepository.save(user)

            logger.info("Cadastro realizado com sucesso")
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor)
        }

    fun login(user: ProfessorRequestDto): ResponseEntity<ProfessorResponseDto> =
        professorRepository.findByEmail(user.email)?.takeIf { codSenha.matches(user.senha, it.senha) }
            ?.let {
                logger.info("Login realizado com sucesso")
                ResponseEntity.ok(ProfessorResponseDto(it))
            } ?: run {
            logger.error("O usuário ou senha esta incorreto")
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "O usuário ou senha esta incorreto")
        }

}