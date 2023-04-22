package Agora.agora

import org.hibernate.validator.constraints.br.CPF
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
data class ProdutoDto(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idAgendamento: Int,
    @field:NotBlank var codigoAluno:Int,
    @field:NotBlank var codigoProfessor:Int,
    var dataHora:LocalDate,
    @field:NotBlank var linkAula:String,
    @field:NotBlank var duracao:String,
) {
}