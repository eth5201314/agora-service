package Agora.agora.dominio.entities

import javax.persistence.*
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.*

@Entity
data class Professor(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val idProfessor: Int,
    var nome: String,
    @field:NotBlank @field:Email
    var email: String,
    @Pattern(
        regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
        message = "Envie um telefone válido"
    )
    var telefone: String = "",
    @field:NotBlank
    var senha: String,
    @field:NotNull
    var idLattes: Long,
    @field:NotBlank
    var formacao: String,
    @ManyToMany
    @JoinTable(
        name = "professor_materia",
        joinColumns = [JoinColumn(name = "id_professor")],
        inverseJoinColumns = [JoinColumn(name = "id_materia")]
    )
    var materias: MutableList<Materia> = mutableListOf()
)