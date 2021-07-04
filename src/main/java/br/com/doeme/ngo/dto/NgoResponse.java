package br.com.doeme.ngo.dto;

import br.com.doeme.user.entiry.User;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class NgoResponse implements Serializable {

    private static final long serialVersionUID = -1021663214420362107L;

    private Long id;
    private User user;
}
