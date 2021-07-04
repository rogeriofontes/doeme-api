package br.com.doeme.necessity.model.entity;


import br.com.doeme.beneficiary.model.entity.Beneficiary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_necessity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Necessity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String local;

    @Size(min = 3, max = 256, message = "O texto só pode ter 256 caracteres")
    private String necessity;

    private String pic;

    @ManyToOne
    @JoinColumn(name = "beneficiary_id")
    private Beneficiary beneficiary;

    private String code;

    public void update(Long id, Necessity necessity) {
        this.id = id;
        this.local = necessity.getLocal();
        this.necessity = necessity.getNecessity();
        this.pic = necessity.getPic();
        this.beneficiary = necessity.getBeneficiary();
    }
}
