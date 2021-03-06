package br.com.doeme.donor.dto;

import lombok.*;

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
    private Long userId;
}