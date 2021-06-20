package br.com.doeme.fornecedor.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class FornecedorRequest implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private String name;
    private String email;

}
