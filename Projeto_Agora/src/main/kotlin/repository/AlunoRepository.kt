package repository

import dtoAluno.AlunoDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import javax.transaction.Transactional

interface AlunoRepository:JpaRepository<AlunoDto,Int>{

    @Transactional
    @Modifying
    @Query("INSERT INTO Aluno (nome, genero, dtNascAluno, email, senha) VALUES (:nome, :genero, :dtNascAluno, :email, :senha)")
    fun salvarAluno(
        @Param("nome") nome: String,
        @Param("genero") genero: String,
        @Param("dtNascAluno") dtNasc: LocalDate,
        @Param("email") email: String,
        @Param("senha") senha: String,
    )


}