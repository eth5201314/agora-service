package com.agora.services.checkingaccount.application.controller

import com.agora.services.checkingaccount.domain.entities.Estudante
import com.agora.services.checkingaccount.domain.entities.dto.EstudanteRequestDto
import com.agora.services.checkingaccount.domain.entities.dto.EstudanteResponseDto
import com.agora.services.checkingaccount.domain.services.EstudanteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/estudante")
class EstudanteController(
    val estudanteService: EstudanteService
) {


    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid user: Estudante): ResponseEntity<Estudante> =
        estudanteService.createUser(user = user)

    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody user: EstudanteRequestDto): ResponseEntity<EstudanteResponseDto> =
        estudanteService.login(user = user)
}




