package Agora.agora

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class Aluno(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    @field:NotBlank
    var nome: String,
    @field:NotBlank @field:Email var email: String,
    @field:NotBlank var senha: String
)