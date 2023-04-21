package Agora.agora

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface AlunoRepository: JpaRepository<AlunoDto, Int> {

//    fun salvarAluno(aluno: AlunoDto):AlunoDto {
//        return save(aluno)
//   }

}