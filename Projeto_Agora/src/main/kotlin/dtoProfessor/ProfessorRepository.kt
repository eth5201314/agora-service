package dtoProfessor

import org.springframework.data.jpa.repository.JpaRepository

interface ProfessorRepository:JpaRepository<Professor, Int> {
}