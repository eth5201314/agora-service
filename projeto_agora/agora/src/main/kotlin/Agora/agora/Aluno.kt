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
data class Aluno (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int,
    @field:NotBlank var nome:String,
    @field:Size(min = 1, max = 1) var genero: String,
    @field:CPF var cpf:String,
    @field:Past var dtNasc: LocalDate,// apagar
    @field:NotBlank @field:Email var email:String,
    @field:NotBlank var senha:String,

    ) {
}