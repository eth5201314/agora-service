package dtoAluno

import org.springframework.data.jpa.repository.JpaRepository

interface AlunoRepository:JpaRepository<Aluno,Int>{
}