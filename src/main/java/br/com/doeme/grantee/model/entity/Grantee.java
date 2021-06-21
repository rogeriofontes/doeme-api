package br.com.doeme.grantee.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_grantee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Grantee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    private String name;

    private String local;

    private String code;

    @Size(min = 3, max = 256, message = "O texto só pode ter 256 caracteres")
    private String necessity;

    private String pic;

    @NotNull(message = "O e-mail não pode estar vazio")
    @Email
    private String email;

    public void update(Long id, Grantee grantee) {
        this.id = id;
        this.name = grantee.getName();
        this.email = grantee.getEmail();
        this.local = grantee.getLocal();
        this.necessity = grantee.getNecessity();
        this.pic = grantee.getPic();
    }
}
