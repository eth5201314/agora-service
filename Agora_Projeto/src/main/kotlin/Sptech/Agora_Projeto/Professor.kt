package Sptech.Agora_Projeto

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past
import javax.validation.constraints.Size

@Entity
data class Professor (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val idProfessor: Int,
    var nomeProfessor:String,
    @field:Size(min = 1, max = 1) var generoProfessor: String,
    @field:NotBlank var cpfProfessor:String,
    @field:Past var dtNascProfessor: LocalDate,
    @field:NotBlank @field:Email var emailProfessor:String,
    @field:NotBlank var senha:String,
    @field:Size(min = 11, max = 11) var contato:Int,
    @field:NotBlank var idLattes:Int,
    ) {
}