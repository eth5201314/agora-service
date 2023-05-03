package com.agora.services.checkingaccount.domain.entities.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class EstudanteRequestDto(
    @JsonProperty("email") val email: String,
    @JsonProperty("senha") var senha: String
)