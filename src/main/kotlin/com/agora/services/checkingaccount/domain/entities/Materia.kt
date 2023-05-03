package com.agora.services.checkingaccount.domain.entities

import com.agora.services.checkingaccount.domain.enum.AreaDeEnsino
import javax.persistence.*

@Entity
data class Materia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @field:Column(length = 45)
    @Enumerated(EnumType.STRING)
    val nome: AreaDeEnsino?
)