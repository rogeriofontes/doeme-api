package br.com.doeme.donation.model.entity;


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
public class DonationApprove implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;

    private boolean approved;

    public void update(Long id, DonationApprove donation) {
        this.id = id;
        this.donation = donation.getDonation();
        this.approved = donation.isApproved();
        this.ngo = donation.getNgo();
    }
}
