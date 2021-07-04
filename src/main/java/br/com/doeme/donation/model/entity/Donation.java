package br.com.doeme.donation.model.entity;


import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.necessity.model.entity.Necessity;
import br.com.doeme.ngo.model.entity.Ngo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_donation")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Donation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "necessity_id")
    private Necessity necessity;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;

    @Size(min = 3, max = 256, message = "O texto só pode ter 256 caracteres")
    private String donation;

    private int amount;

    private boolean approved;

    private String code;

    public void update(Long id, Donation donation) {
        this.id = id;
        this.necessity = donation.getNecessity();
        this.donor = donation.getDonor();
        this.donation = donation.getDonation();
        this.amount = donation.getAmount();
        this.approved = donation.isApproved();
        this.ngo = donation.getNgo();
    }
}
