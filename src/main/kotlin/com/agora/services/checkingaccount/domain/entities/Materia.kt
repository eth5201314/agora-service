package com.agora.services.checkingaccount.domain.entities

import com.agora.services.checkingaccount.domain.enum.AreaDeEnsino
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Materia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @field:Column(length = 45)
    @field:NotNull
    @Enumerated(EnumType.STRING)
    val nome: AreaDeEnsino?
)