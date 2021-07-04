package br.com.doeme.user.dto;

import br.com.doeme.user.entiry.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse implements Serializable {

    private static final long serialVersionUID = 5060069325990956788L;

    private Long id;
    private Long ongId;
    private Long donorId;
    private Long beneficiaryId;
    private User user;
}
