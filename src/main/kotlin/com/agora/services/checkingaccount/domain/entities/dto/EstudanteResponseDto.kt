package com.agora.services.checkingaccount.domain.entities.dto

import com.agora.services.checkingaccount.domain.entities.dto.EstudanteRequestDto

data class EstudanteResponseDto(
    val email: String
) {
    constructor(estudante: EstudanteRequestDto) : this(
        estudante.email
    )
}