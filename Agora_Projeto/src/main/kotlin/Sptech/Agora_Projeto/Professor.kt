package Sptech.Agora_Projeto

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Professor (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val idAluno: Int,
    var nomeProfessor:String,
    var generoProfessor: String,
    var cpfProfessor:String,
    var dtNascProfessor: LocalDate,
    var emailProfessor:String,
    var senha:String,
    var contato:Int,
    var idLattes:Int,
    ) {
}