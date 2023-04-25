package Agora.agora.controller

import Agora.agora.dominio.dao.ProfessorResponseDao
import Agora.agora.dominio.dto.ProfessorRequestDto
import Agora.agora.dominio.entities.Professor
import Agora.agora.repository.ProfessorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/professor")
class ProfessorController(val professorRepositorio: ProfessorRepository) {

    private val codSenha = BCryptPasswordEncoder()
    private val restTemplate = RestTemplate()
    private val apiUrl = "http://localhost:5000/lattes/"

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid professor: Professor): ResponseEntity<Professor> {
        // Verifica se o e-mail já está cadastrado no banco
        val existingProfessor = professorRepositorio.findByEmail(professor.email)

        return if (existingProfessor != null) {
            throw ResponseStatusException(409, "O e-mail ${professor.email} já existe no sistema", null)
        } else {

            // Verifica se o número de identificação Lattes é válido
            val lattesResponse = restTemplate.getForEntity("$apiUrl${professor.idLattes}", String::class.java)
            if (lattesResponse.statusCode != HttpStatus.OK) {
                throw ResponseStatusException(404, "O id Lattes ${professor.idLattes} é inválido", null)
            }

            try {
                // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
                val senhaCodificada = codSenha.encode(professor.senha)

                // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
                professor.senha = senhaCodificada

                // Salva o objeto modificado no banco
                professorRepositorio.save(professor)
                return ResponseEntity.status(201).body(professor)

            } catch (ex: Exception) {
                println("Erro na execução!")
                ResponseEntity.status(400).body(null)
            }
        }
    }

    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody login: ProfessorRequestDto): ResponseEntity<ProfessorResponseDao> {
        // Realiza a consulta no banco de dados pelo email informado e guarda o resultado em uma variável
        val retornado = professorRepositorio.findByEmail(login.email)

        /// Checa se a senha do objeto retornado é igual a senha que foi informada
        return if (retornado != null && (codSenha.matches(login.senha, retornado.senha))) {
            println("Login realizado com sucesso")
            val professorResponse = ProfessorResponseDao(retornado)
            ResponseEntity.status(200).body(professorResponse)
        } else {
            println("Usuário ou senha incorretos")
            ResponseEntity.status(404).body(null)
        }
    }
}