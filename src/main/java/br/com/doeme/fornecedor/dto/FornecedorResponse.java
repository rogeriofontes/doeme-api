package br.com.doeme.fornecedor.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class FornecedorResponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    //@Min(3)
    //@Max(60)
    //@Pattern(regexp = "[a-zA-Z0-9]+", message = "must not contain special characters")
    private String name;

    @NotNull(message = "O e-mail não pode estar vazio")
    @Email
    private String email;

    public void update(Long id, FornecedorResponse fornecedor) {
        this.id = id;
        this.name = fornecedor.getName();
        this.email = fornecedor.getEmail();
    }
}
