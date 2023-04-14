package Sptech.Agora_Projeto

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past
import javax.validation.constraints.Size

@Entity
data class Aluno (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val idAluno: Int,
    @field:NotBlank var nomeAluno:String,
    @field:Size (min = 1, max = 1) var generoAluno: String,
    var cpfAluno:String,
    @field:Past var dtNascAluno:LocalDate,
    @field:NotBlank @field:Email var emailAluno:String,
    @field:NotBlank var senhaAluno:String,

    ) {
}