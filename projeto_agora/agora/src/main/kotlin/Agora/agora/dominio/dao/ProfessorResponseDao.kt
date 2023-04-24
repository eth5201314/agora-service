package Agora.agora.dominio.dao

import Agora.agora.dominio.dto.ProfessorRequestDto

data class ProfessorResponseDao(
    val email: String
) {
    constructor(professor: ProfessorRequestDto) : this(
        professor.email
    )
}