package com.agora.services.checkingaccount.domain.entities.dto

data class EstudanteResponseDto(
    val email: String?
) {
    constructor(estudante: EstudanteRequestDto) : this(
        estudante.email
    )
}