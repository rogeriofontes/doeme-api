package br.com.doeme.doacao.model.entity;

import br.com.doeme.beneficiario.model.entity.Beneficiario;
import br.com.doeme.doador.model.entity.Doador;
import br.com.doeme.fornecedor.model.entity.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_doacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_doador")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "id_beneficiario")
    private Beneficiario beneficiario;

}
