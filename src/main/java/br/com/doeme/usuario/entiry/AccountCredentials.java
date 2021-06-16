package br.com.doeme.usuario.entiry;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, of = {"email", "password"})
public class AccountCredentials implements Serializable {
    static final long serialVersionUID = -3147203146262241574L;
    private String username;
    private String password;
}
