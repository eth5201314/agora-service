package Agora.agora.controller

import Agora.agora.dominio.dao.EstudanteResponseDao
import Agora.agora.dominio.dto.EstudanteRequestDto
import Agora.agora.dominio.entities.Estudante
import Agora.agora.repository.EstudanteRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/estudante")
class EstudanteController(val alunoRepositorio: EstudanteRepository) {

    val codSenha = BCryptPasswordEncoder()

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid aluno: Estudante): ResponseEntity<Estudante> {
        return try {
            // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
            val senhaCodificada = codSenha.encode(aluno.senha)

            // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
            aluno.senha = senhaCodificada

            // Salva o objeto modificado no banco
            alunoRepositorio.save(aluno)
            ResponseEntity.status(201).body(aluno)
        } catch (ex: Exception) {
            println("Erro na execução!")
            ResponseEntity.status(400).body(null)
        }
    }

    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody login: EstudanteRequestDto): ResponseEntity<EstudanteResponseDao> {
        // Realiza a consulta no banco de dados pelo email informado e guarda o resultado em uma variável
        val retornado = alunoRepositorio.findByEmail(login.email)

        // Checa se a senha do objeto retornado é igual a senha que foi informada
        return if (codSenha.matches(login.senha, retornado.senha)) {
            println("Login realizado com sucesso")
            val estudanteResponse = EstudanteResponseDao(retornado)
            ResponseEntity.status(200).body(estudanteResponse)
        } else {
            println("Usuário ou senha incorretos")
            ResponseEntity.status(404).body(null)
        }
    }
}




