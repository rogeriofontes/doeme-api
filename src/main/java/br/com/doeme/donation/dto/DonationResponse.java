package br.com.doeme.donation.dto;

import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.necessity.model.entity.Necessity;
import br.com.doeme.ngo.model.entity.Ngo;
import lombok.*;

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
    private Long necessityId;
    private Long donorId;
    private String donation;
    private String code;
}
