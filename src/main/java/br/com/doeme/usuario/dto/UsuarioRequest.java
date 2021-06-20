package br.com.doeme.usuario.dto;

import br.com.doeme.usuario.entiry.Perfil;
import br.com.doeme.usuario.entiry.UserType;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class UsuarioRequest implements Serializable {

    private static final long serialVersionUID = 7611225270974260535L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String userType;
}