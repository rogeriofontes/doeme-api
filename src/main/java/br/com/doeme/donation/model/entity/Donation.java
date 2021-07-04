package br.com.doeme.donation.model.entity;


import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.grantee.model.entity.Grantee;
import br.com.doeme.ong.model.entity.Ong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @JoinColumn(name = "grantee_id")
    private Grantee grantee;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @Size(min = 3, max = 256, message = "O texto só pode ter 256 caracteres")
    private String donation;

    private int amount;

    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;

    private String code;

    public void update(Long id, Donation donation) {
        this.id = id;
        this.grantee = donation.getGrantee();
        this.donor = donation.getDonor();
        this.donation = donation.getDonation();
        this.amount = donation.getAmount();
        this.approved = donation.isApproved();
        this.ong = donation.getOng();
    }
}
