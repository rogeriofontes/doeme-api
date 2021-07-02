package br.com.doeme.donor.model.entity;


import br.com.doeme.user.entiry.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_donor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Donor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String local;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public void update(Long id, Donor donor) {
        this.id = id;
        this.local = donor.getLocal();
    }
}
