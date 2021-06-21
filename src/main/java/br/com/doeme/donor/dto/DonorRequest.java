package br.com.doeme.donor.dto;

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
    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    private String name;
    private String local;
    private String email;
    private String code;

}