package com.agora.services.checkingaccount.application.config

import com.agora.services.checkingaccount.domain.entities.Materia
import com.agora.services.checkingaccount.domain.enum.AreaDeEnsino
import com.agora.services.checkingaccount.application.repository.MateriaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(private val materiaRepository: MateriaRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        loadMaterias()
    }

    private fun loadMaterias() {
        if (materiaRepository.count() == 0L) {
            val materias = mutableListOf<Materia>()
            for (areaDeEnsino in AreaDeEnsino.values()) {
                materias.add(Materia(id = null, nome = areaDeEnsino))
            }
            materiaRepository.saveAll(materias)
        }
    }
}