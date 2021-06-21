package br.com.doeme.beneficiario.model.entity;

import br.com.doeme.doador.model.entity.Doador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_beneficiario")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    public void update(Long id, Beneficiario benef) {
        this.id     = id;
        this.nome   = benef.getNome();
        this.email  = benef.getEmail();
    }

}
