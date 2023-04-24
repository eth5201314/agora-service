package Agora.agora.dominio.entities

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Solicitacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSolicitacao: Int,
    @field:NotBlank
    var codigoAluno: Int,
    @field:NotBlank
    var codigoProfessor: Int,
    var status: Boolean,
    @field:NotBlank
    var dataHora: LocalDateTime
)