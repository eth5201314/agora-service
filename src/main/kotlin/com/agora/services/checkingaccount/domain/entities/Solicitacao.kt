package com.agora.services.checkingaccount.domain.entities

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Solicitacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSolicitacao: Int?,

    @ManyToOne
    @field:NotNull
    var codigoAluno: Estudante?,

    @ManyToOne
    @field:NotNull
    var codigoProfessor: Professor?,

    var aceite: LocalDateTime?,

    @field:CreationTimestamp
    var dataHora: LocalDateTime?
)