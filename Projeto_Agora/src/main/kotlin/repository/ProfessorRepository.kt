package repository

import dtoProfessor.ProfessorDto
import org.springframework.data.jpa.repository.JpaRepository

interface ProfessorRepository:JpaRepository<ProfessorDto, Int> {
}