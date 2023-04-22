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
import javax.validation.Valid

@RestController
@RequestMapping("/aluno")

class AlunoController() {

    @Autowired
    lateinit var alunoRepositorio: AlunoRepository
    lateinit var loginRepositorio: AlunoRequestDto


    val codSenha = BCryptPasswordEncoder()

    @PostMapping
    fun cadastrar(@RequestBody @Valid aluno: AlunoDto): Any {

        try {
            // Geração do salt
            val random = SecureRandom()
            val salt = ByteArray(16)
            random.nextBytes(salt)

            // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
            val senhaCodificada = codSenha.encode(aluno.senha)

            // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
            aluno.senha = String(salt) + senhaCodificada

            // Salva o objeto modificado no banco
            alunoRepositorio.save(aluno)
            return ResponseEntity.status(201).body(aluno)
        } catch (ex: Exception) {
            println("Erro na execução!")
            return ResponseEntity.status(400).body(null)
        }
    }

    @GetMapping("/login")

    fun login(login:AlunoRequestDto):Any{

        try {
            // Geração do salt
            val random = SecureRandom()
            val salt = ByteArray(16)
            random.nextBytes(salt)

            // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
            val senhaCodificada = codSenha.encode(login.senha)

            // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
            login.senha = String(salt) + senhaCodificada

            return ResponseEntity.status(201).body(login)
        } catch (ex: Exception) {
            println("Erro na execução!")
            return ResponseEntity.status(400).body(null)
        }
    }
    }



