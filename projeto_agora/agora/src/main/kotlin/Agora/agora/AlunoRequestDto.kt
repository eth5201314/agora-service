package Agora.agora

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class AlunoRequestDto(
    @field:NotBlank @Email val email: String,
    @field:NotBlank var senha: String
)