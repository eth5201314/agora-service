package Agora.agora

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.*

@Entity
data class Professor (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val idProfessor: Int,
    var nome:String,
    @field:Size(min = 1, max = 1) var genero: String,
    //@field:NotBlank @field:CPF var cpfProfessor:String,
    @field:Past var dtNasc: LocalDate,
    @field:NotBlank @field:Email var email:String,
    @field:NotBlank var senha:String,
    @Pattern(
        regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
        message = "Envie um telefone válido"
    )
    var contato: String = "",
    @field:NotBlank var idLattes:String,
) {
}