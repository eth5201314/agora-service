package Agora.agora.dominio.entities

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Materia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @field:NotBlank
    @field:Column(length = 45)
    val nome: String
)