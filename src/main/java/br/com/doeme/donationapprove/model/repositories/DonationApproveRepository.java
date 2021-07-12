package br.com.doeme.donationapprove.model.repositories;

import br.com.doeme.donationapprove.model.entity.DonationApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationApproveRepository extends JpaRepository<DonationApprove, Long> {
}
