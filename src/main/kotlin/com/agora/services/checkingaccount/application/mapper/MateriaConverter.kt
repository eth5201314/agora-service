package com.agora.services.checkingaccount.application.mapper

import com.agora.services.checkingaccount.domain.entities.Materia
import com.agora.services.checkingaccount.application.repository.MateriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class MateriaConverter : Function<String?, Materia?> {
    @Autowired
    private val materiaRepository: MateriaRepository? = null
    override fun apply(source: String?): Materia? {
        return if (source == null) {
            null
        } else try {
            val id = source.toInt()
            materiaRepository!!.findById(id).orElse(null)
        } catch (e: NumberFormatException) {
            null
        }
    }
}