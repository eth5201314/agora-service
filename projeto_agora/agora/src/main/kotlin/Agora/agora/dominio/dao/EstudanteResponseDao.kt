package Agora.agora.dominio.dao

import Agora.agora.dominio.dto.EstudanteRequestDto

data class EstudanteResponseDao(
    val email: String
) {
    constructor(estudante: EstudanteRequestDto) : this(
        estudante.email
    )
}