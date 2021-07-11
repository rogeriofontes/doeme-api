package br.com.doeme.donation.dto;

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
public class DonationRequest implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private Long necessityId;
    private Long donorId;
    @NotNull(message = "O texto só pode ter 256 caracteres")
    @Size(min = 3, max = 60)
    private String donation;
    private String code;
}