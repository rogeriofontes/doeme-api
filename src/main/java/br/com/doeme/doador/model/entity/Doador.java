package br.com.doeme.doador.model.entity;

import br.com.doeme.fornecedor.model.entity.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_doador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    public void update(Long id, Doador doador) {
        this.id     = id;
        this.nome   = doador.getNome();
        this.email  = doador.getEmail();
    }

}
