package com.agora.services.checkingaccount.domain.entities

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class Estudante(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEstudante: Int?,

    @field:NotBlank
    @field:Column(length = 45)
    var nome: String?,

    @field:NotBlank
    @field:Email
    @field:Column(length = 45)
    var email: String?,

    @field:NotBlank
    var senha: String?
)