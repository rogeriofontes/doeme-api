package br.com.doeme.user.dto;

import br.com.doeme.user.entiry.Profile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = {"userId", "token", "roles"})
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = 782768836912120463L;
    private Long userId;
    private Long ongId;
    private Long donorId;
    private Long beneficiaryId;
    private String token;
    private Set<Profile> profiles;
}
