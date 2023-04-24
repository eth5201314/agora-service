package Agora.agora

import org.springframework.data.jpa.repository.JpaRepository

interface ProfessorRepository: JpaRepository<Professor, Int> {

    fun findByEmail(professorEmail: String): ProfessorLoginDto
}