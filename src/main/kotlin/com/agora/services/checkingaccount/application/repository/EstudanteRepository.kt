package com.agora.services.checkingaccount.application.repository

import com.agora.services.checkingaccount.domain.entities.dto.EstudanteRequestDto
import com.agora.services.checkingaccount.domain.entities.Estudante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.validation.annotation.Validated

@RepositoryRestResource(path = "estudantes")
@Validated
interface EstudanteRepository : JpaRepository<Estudante, Int> {
    fun findByEmail(alunoEmail: String): EstudanteRequestDto?
}