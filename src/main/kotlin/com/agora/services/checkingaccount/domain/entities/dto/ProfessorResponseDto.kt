package com.agora.services.checkingaccount.domain.entities.dto

import com.agora.services.checkingaccount.domain.entities.dto.ProfessorRequestDto

data class ProfessorResponseDto(
    val email: String
) {
    constructor(professor: ProfessorRequestDto) : this(
        professor.email
    )
}