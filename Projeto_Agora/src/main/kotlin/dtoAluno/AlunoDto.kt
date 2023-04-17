package dtoAluno

//import org.hibernate.validator.constraints.br.CPF
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.transaction.Transactional
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past
import javax.validation.constraints.Size


@Entity
 class AlunoDto (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idAluno: Int,
    @field:NotBlank var nome:String,
    @field:Size (min = 1, max = 1) var genero: String,
   // @field:CPF var cpfAluno:String,
    @field:Past var dtNasc: LocalDate,
    @field:NotBlank @field:Email var email:String,
    @field:NotBlank var senha:String,

    ) {



}