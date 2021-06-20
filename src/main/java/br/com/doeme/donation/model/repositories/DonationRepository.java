package br.com.doeme.donation.model.repositories;

import br.com.doeme.donation.model.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
}
