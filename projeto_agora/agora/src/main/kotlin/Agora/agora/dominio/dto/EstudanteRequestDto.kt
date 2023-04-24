package Agora.agora.dominio.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class EstudanteRequestDto(
    val email: String,
    var senha: String
)