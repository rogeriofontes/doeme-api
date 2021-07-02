package br.com.doeme.user.dto;

import br.com.doeme.user.entiry.Profile;
import br.com.doeme.user.entiry.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 7611225270974260535L;

    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private UserType userType;
    private Set<Profile> profiles;
    private String code;
}
