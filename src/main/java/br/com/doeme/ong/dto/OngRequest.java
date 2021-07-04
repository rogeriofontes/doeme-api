package br.com.doeme.ong.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class OngRequest implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private Long userId;
}