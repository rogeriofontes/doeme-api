package br.com.doeme.donor.model.entity;


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

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    private String name;

    private String local;

    private String code;

    @NotNull(message = "O e-mail não pode estar vazio")
    @Email
    private String email;

    public void update(Long id, Donor donor) {
        this.id = id;
        this.name = donor.getName();
        this.local = donor.getLocal();
        this.email = donor.getEmail();
    }
}
