package com.agora.services.checkingaccount.application.repository

import com.agora.services.checkingaccount.domain.entities.Materia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.validation.annotation.Validated

@RepositoryRestResource(path = "materias")
@Validated
interface MateriaRepository : JpaRepository<Materia, Int>