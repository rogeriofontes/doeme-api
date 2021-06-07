package br.com.doeme.pessoa.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_pessoa")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)

    @Pattern(regexp = "[a-zA-Z0-9]+", message = "must not contain special characters")
    private String name;

    @NotNull(message = "O e-mail não pode estar vazio")
    @Email
    private String email;

    @NotNull(message = "O e-mail não pode estar vazio")
    @CPF
    @Min(11)
    @Max(11)
    private String cpf;

    public void update(Long id, Pessoa pessoa) {
        this.id = id;
        this.name = pessoa.getName();
        this.email = pessoa.getEmail();
    }
}
