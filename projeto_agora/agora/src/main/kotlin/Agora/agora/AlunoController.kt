package Agora.agora

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.SecureRandom
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/aluno")

class AlunoController(val alunoRepositorio: AlunoRepository) {

    val codSenha = BCryptPasswordEncoder()

    @PermitAll
    @PostMapping
    fun cadastrar(@RequestBody @Valid aluno: Aluno): ResponseEntity<Aluno> {

        try {

            // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
            val senhaCodificada = codSenha.encode(aluno.senha)

            // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
            aluno.senha = senhaCodificada

            // Salva o objeto modificado no banco
            alunoRepositorio.save(aluno)
            return ResponseEntity.status(201).body(aluno)
        } catch (ex: Exception) {
            println("Erro na execução!")
            return ResponseEntity.status(400).body(null)
        }
    }

    @PermitAll
    @PostMapping("/login")
    fun login(@RequestBody login:AlunoRequestDto): ResponseEntity<AlunoRequestDto> {

            // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
            val senhaCodificada = codSenha.encode(login.senha)

            // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
            login.senha = senhaCodificada

            // Realiza a consulta no banco de dados pelo email informado e guarda o resultado em uma variável
            val retornado = alunoRepositorio.findByEmail(login.email)

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




