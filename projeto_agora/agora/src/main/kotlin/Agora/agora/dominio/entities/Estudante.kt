package Agora.agora.dominio.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class Estudante(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEstudante: Int,
    @field:NotBlank
    var nome: String,
    @field:NotBlank @field:Email var email: String,
    @field:NotBlank var senha: String
)