package br.com.doeme.donation.dto;

import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.grantee.model.entity.Grantee;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class DonationResponse implements Serializable {

    private static final long serialVersionUID = 4100599494162472544L;

    private Long id;
    private Long granteeId;
    private Long donorId;
    private String donation;
}
