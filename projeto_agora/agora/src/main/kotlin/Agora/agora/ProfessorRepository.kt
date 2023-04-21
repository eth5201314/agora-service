package Agora.agora

import org.springframework.data.jpa.repository.JpaRepository

interface ProfessorRepository: JpaRepository<ProfessorDto, Int> {
}