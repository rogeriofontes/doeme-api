package br.com.doeme.donationapprove.v1;

import br.com.doeme.donationapprove.dto.DonationApproveMapper;
import br.com.doeme.donationapprove.dto.DonationApproveRequest;
import br.com.doeme.donationapprove.dto.DonationApproveResponse;
import br.com.doeme.donationapprove.model.entity.DonationApprove;
import br.com.doeme.donationapprove.model.service.DonationApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/donation-approves")
public class DonationApproveResources {

    @Autowired
    private DonationApproveService donationApproveService;

    @Autowired
    private DonationApproveMapper donationApproveMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DonationApproveResponse>> list() {
        List<DonationApprove> donationApproveList = donationApproveService.list();
        List<DonationApproveResponse> donationApproveResponseLists = donationApproveMapper.map(donationApproveList);

        if (donationApproveResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donationApproveResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DonationApprove> finById(@PathVariable("id") Long id) {
        Optional<DonationApprove> donationApproves = donationApproveService.findById(id);

        if (!donationApproves.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donationApproves.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<DonationApproveResponse> save(@Valid @RequestBody DonationApproveRequest donationApproveRequest) throws Exception {
        DonationApprove donationApprove = donationApproveMapper.from(donationApproveRequest);
        DonationApprove saved = donationApproveService.save(donationApprove);
        DonationApproveResponse donationApproveResponse = donationApproveMapper.to(saved);

        if (donationApproveResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(donationApproveResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DonationApproveResponse> update(@PathVariable("id") Long id, @Valid @RequestBody DonationApproveRequest donationApproveRequest) {
        DonationApprove donationApprove = donationApproveMapper.from(donationApproveRequest);
        DonationApprove updated = donationApproveService.update(id, donationApprove);
        DonationApproveResponse donationApproveResponse = donationApproveMapper.to(updated);

        if (donationApproveResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donationApproveResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        donationApproveService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
