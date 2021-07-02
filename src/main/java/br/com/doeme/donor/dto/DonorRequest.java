package br.com.doeme.donor.dto;

import br.com.doeme.user.entiry.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class DonorRequest implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private String local;
    private Long userId;
}