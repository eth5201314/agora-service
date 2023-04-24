package Agora.agora.repository

import Agora.agora.dominio.entities.Solicitacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.validation.annotation.Validated

@RepositoryRestResource(path = "agendamentos")
@Validated
interface AgendamentoRepository : JpaRepository<Solicitacao, Int>