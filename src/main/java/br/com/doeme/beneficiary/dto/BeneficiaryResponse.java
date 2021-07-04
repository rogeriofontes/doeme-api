package br.com.doeme.beneficiary.dto;

import br.com.doeme.grantee.model.entity.Grantee;
import br.com.doeme.user.entiry.User;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class BeneficiaryResponse implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private User user;
}
