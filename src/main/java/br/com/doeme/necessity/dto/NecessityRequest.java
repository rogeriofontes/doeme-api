package br.com.doeme.necessity.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class NecessityRequest implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private String local;
    @Size(min = 3, max = 256, message = "O texto só pode ter 256 caracteres")
    private String necessity;
    private String pic;
    private Long beneficiaryId;
}