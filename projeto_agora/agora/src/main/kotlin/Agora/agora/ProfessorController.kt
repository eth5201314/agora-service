package Agora.agora

import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.PermitAll
import javax.validation.Valid
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("/professor")

class ProfessorController(val professorRepositorio: ProfessorRepository) {

    val codSenha = BCryptPasswordEncoder()

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid professor: Professor): ResponseEntity<Professor> {

//
//            val client = WebClient.create("http://localhost:5000") // Cria uma instância do WebClient apontando para a API na porta 5000
//
//            val response = client.get() // Define o método HTTP GET
//                .uri("/lattes/${professor.idLattes}") // Define o endpoint da API que será acessado
//                .retrieve() // Inicia a recuperação da resposta da API
//                .bodyToMono(String::class.java) // Define o tipo de resposta que será recebido
//                .block() // Bloqueia o fluxo até que a resposta seja recebida
//
//            println(response) // Imprime a resposta recebida

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
    fun login(@RequestBody login:ProfessorLoginDto): ResponseEntity<ProfessorLoginDto> {

        // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
        val senhaCodificada = codSenha.encode(login.senha)

        // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
        login.senha = senhaCodificada

        // Realiza a consulta no banco de dados pelo email informado e guarda o resultado em uma variável
        val retornado = professorRepositorio.findByEmail(login.email)

        // Checa se a senha do objeto retornado é igual a senha que foi informada
        if (codSenha.matches(login.senha, retornado.senha)) {
            println("Login realizado com sucesso")
            return ResponseEntity.status(201).body(login)
        } else {
            println("Usuário ou senha incorretos")
            return ResponseEntity.status(404).body(null)
        }
    }
}