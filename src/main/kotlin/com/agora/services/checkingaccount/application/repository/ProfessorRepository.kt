package com.agora.services.checkingaccount.application.repository

import com.agora.services.checkingaccount.domain.entities.Professor
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorRequestDto
import com.agora.services.checkingaccount.domain.entities.dto.ProfessorResponseDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.validation.annotation.Validated

@RepositoryRestResource(path = "professores")
@Validated
interface ProfessorRepository: JpaRepository<Professor, Int> {
    fun findByEmail(professorEmail: String): ProfessorRequestDto?
    fun findByIdLattes(idLattes: Long): ProfessorResponseDto?
}