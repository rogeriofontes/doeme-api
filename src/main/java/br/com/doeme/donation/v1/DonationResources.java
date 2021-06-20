package br.com.doeme.donation.v1;

import br.com.doeme.donation.dto.DonationMapper;
import br.com.doeme.donation.dto.DonationRequest;
import br.com.doeme.donation.dto.DonationResponse;
import br.com.doeme.donation.model.entity.Donation;
import br.com.doeme.donation.model.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/donations")
public class DonationResources {

    @Autowired
    private DonationService donationService;

    @Autowired
    private DonationMapper donationMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DonationResponse>> list() {
        List<Donation> donationList = donationService.list();
        List<DonationResponse> donationResponseLists = donationMapper.map(donationList);

        if (donationResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donationResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Donation> finById(@PathVariable("id") Long id) {
        Optional<Donation> fornecedores = donationService.findById(id);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<DonationResponse> save(@Valid @RequestBody DonationRequest donationRequest) throws Exception {
        Donation donation = donationMapper.from(donationRequest);
        Donation saved = donationService.save(donation);
        DonationResponse donationResponse = donationMapper.to(saved);

        if (donationResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(donationResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DonationResponse> update(@PathVariable("id") Long id, @Valid @RequestBody DonationRequest donationRequest) {
        Donation donation = donationMapper.from(donationRequest);
        Donation updated = donationService.update(id, donation);
        DonationResponse donationResponse = donationMapper.to(updated);

        if (donationResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donationResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        donationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
