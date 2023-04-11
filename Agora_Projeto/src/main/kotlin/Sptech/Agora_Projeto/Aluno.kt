package Sptech.Agora_Projeto

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Aluno (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val idAluno: Int,
    var nomeAluno:String,
    var generoAluno: String,
    var cpfAluno:String,
    var dtNascAluno:LocalDate,
    var emailAluno:String,
    var senhaAluno:String,

    ) {
}