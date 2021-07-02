package br.com.doeme.grantee.dto;

import br.com.doeme.user.entiry.User;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class GranteeRequest implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private String local;
    @Size(min = 3, max = 256, message = "O texto só pode ter 256 caracteres")
    private String necessity;
    private String pic;
    private Long userId;
}