package br.com.doeme.usuario.dto;

import br.com.doeme.usuario.entiry.Perfil;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = {"userId", "token", "roles"})
@Data
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = 782768836912120463L;
    private Long userId;
    private String token;
    private Set<Perfil> roles;
}
