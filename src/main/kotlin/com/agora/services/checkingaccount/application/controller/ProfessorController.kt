package com.agora.services.checkingaccount.application.controller

import com.agora.services.checkingaccount.domain.entities.Professor
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorRequestDto
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorResponseDto
import com.agora.services.checkingaccount.domain.entities.services.ProfessorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/professor")
class ProfessorController(
    val professorService: ProfessorService
) {

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid user: Professor): ResponseEntity<Professor> =
        professorService.createUser(user)


    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody user: ProfessorRequestDto): ResponseEntity<ProfessorResponseDto> =
        professorService.login(user)

}