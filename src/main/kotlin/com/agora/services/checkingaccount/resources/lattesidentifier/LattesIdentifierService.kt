package com.agora.services.checkingaccount.resources.lattesidentifier

import com.agora.services.checkingaccount.resources.lattesidentifier.entities.Lattes

class LattesIdentifierService(
    private val client: LattesIdentifierClient
) {
    fun lattesValid(lattes: Lattes): Lattes = client.lattesValid(lattes)
}