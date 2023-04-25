package Agora.agora.repository

import Agora.agora.dominio.dto.EstudanteRequestDto
import Agora.agora.dominio.entities.Estudante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import org.springframework.validation.annotation.Validated

@RepositoryRestResource(path = "estudantes")
@Validated
interface EstudanteRepository : JpaRepository<Estudante, Int> {
    fun findByEmail(alunoEmail: String): EstudanteRequestDto?
}