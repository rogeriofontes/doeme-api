package br.com.doeme.donationapprove.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class DonationApproveResponse implements Serializable {

    private static final long serialVersionUID = 4100599494162472544L;

    private Long id;
    private Long donationId;
    private Long ngoId;
    private boolean approved;
}
