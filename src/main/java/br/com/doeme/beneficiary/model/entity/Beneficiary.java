package br.com.doeme.beneficiary.model.entity;


import br.com.doeme.user.entiry.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Entity
@Table(name = "tb_beneficiary")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Beneficiary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void update(Long id, Beneficiary beneficiary) {
        this.id = id;
        log.info("Beneficiary: {}", beneficiary.toString());
    }
}
