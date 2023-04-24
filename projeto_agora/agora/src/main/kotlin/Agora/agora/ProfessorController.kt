package Agora.agora

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
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

        // Verifica se o número de identificação Lattes é válido
        val lattesResponse = restTemplate.getForEntity("$apiUrl${professor.idLattes}", String::class.java)
        if (lattesResponse.statusCode != HttpStatus.OK) {
            return ResponseEntity.badRequest().body(null) // Retorna Bad Request caso o id Lattes seja inválido
        }

        // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
        val senhaCodificada = codSenha.encode(professor.senha)

        // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
        professor.senha = senhaCodificada

        // Salva o objeto modificado no banco
        professorRepositorio.save(professor)
        return ResponseEntity.status(201).body(professor)
    }

    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody login: ProfessorLoginDto): ResponseEntity<ProfessorLoginDto> {

        // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
        val senhaCodificada = codSenha.encode(login.senha)

        // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
        login.senha = senhaCodificada

        // Realiza a consulta no banco de dados pelo email informado e guarda o resultado em uma variável
        val retornado = professorRepositorio.findByEmail(login.email)

        // Verifica se a consulta retornou algum resultado
        if (codSenha.matches(retornado.senha, login.senha)) {
            println("Login realizado com sucesso")
            return ResponseEntity.status(201).body(login)
        }

        println("Usuário ou senha incorretos")
        return ResponseEntity.status(404).body(null)
    }
}