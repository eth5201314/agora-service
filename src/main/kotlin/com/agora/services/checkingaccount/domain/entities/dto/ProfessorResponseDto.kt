package com.agora.services.checkingaccount.domain.entities.dto

data class ProfessorResponseDto(
    val email: String?
) {
    constructor(professor: ProfessorRequestDto) : this(
        professor.email
    )
}