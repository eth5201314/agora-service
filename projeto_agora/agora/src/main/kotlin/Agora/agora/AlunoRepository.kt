package Agora.agora

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlunoRepository : JpaRepository<Aluno, Int> {
    fun findByEmail(alunoEmail: String): AlunoRequestDto
}