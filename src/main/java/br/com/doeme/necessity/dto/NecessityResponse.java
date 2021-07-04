package br.com.doeme.necessity.dto;

import br.com.doeme.beneficiary.model.entity.Beneficiary;
import br.com.doeme.user.entiry.User;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class NecessityResponse implements Serializable {
    private Long id;
    private String local;
    private String necessity;
    private String pic;
    private Beneficiary beneficiary;
}
