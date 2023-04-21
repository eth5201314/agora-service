package controller

import dtoAluno.AlunoDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import repository.AlunoRepository
import javax.validation.Valid
import java.security.SecureRandom


@RestController
@RequestMapping("/aluno")
class AlunoController(val repositorio: AlunoRepository) {

    val codSenha = BCryptPasswordEncoder()

    @PostMapping
    fun cadastrar(@RequestBody @Valid aluno: AlunoDto): Any {

        //try {
            // Geração do salt
            val random = SecureRandom()
            val salt = ByteArray(16)
            random.nextBytes(salt)

            // Codifica a senha usando o BCryptPasswordEncoder e o salt gerado acima
            val senhaCodificada = codSenha.encode(aluno.senha)

            // Concatena o salt à senha codificada e atualiza o valor da senha no objeto
            aluno.senha = String(salt) + senhaCodificada

            // Salva o objeto modificado no banco
            repositorio.salvarAluno(aluno)
            return ResponseEntity.status(201).body(aluno)
        }
//        catch (ex: Exception){
//            println("Erro na execução!")
//            return ResponseEntity.status(400).body(null)
//        }
    }

//}