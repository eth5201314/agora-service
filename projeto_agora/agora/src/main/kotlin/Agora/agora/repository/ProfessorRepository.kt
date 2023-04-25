package Agora.agora.repository

import Agora.agora.dominio.entities.Professor
import Agora.agora.dominio.dto.ProfessorRequestDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.validation.annotation.Validated

@RepositoryRestResource(path = "professores")
@Validated
interface ProfessorRepository: JpaRepository<Professor, Int> {
    fun findByEmail(professorEmail: String): ProfessorRequestDto?
}