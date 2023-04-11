package Sptech.Agora_Projeto

import org.springframework.data.jpa.repository.JpaRepository

interface ProfessorRepository: JpaRepository<Professor,Int> {
}