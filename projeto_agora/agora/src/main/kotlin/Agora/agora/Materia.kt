package Agora.agora

import javax.persistence.*

@Entity
data class Materia(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val nome: String
)