package br.com.doeme.doeme.fornecedor.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_fornecedor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    //@Min(3)
    //@Max(60)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "must not contain special characters")
    private String name;

    @NotNull(message = "O e-mail não pode estar vazio")
    @Email
    private String email;

    public void update(Long id, Fornecedor fornecedor) {
        this.id = id;
        this.name = fornecedor.getName();
        this.email = fornecedor.getEmail();
    }
}
