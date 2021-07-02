package br.com.doeme.donor.v1;

import br.com.doeme.donor.dto.DonorMapper;
import br.com.doeme.donor.dto.DonorRequest;
import br.com.doeme.donor.dto.DonorResponse;
import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.donor.model.service.DonorService;
import br.com.doeme.grantee.model.entity.Grantee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/donors")
public class DonorResources {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorMapper donorMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DonorResponse>> list() {
        List<Donor> donorList = donorService.list();
        List<DonorResponse> donorResponseLists = donorMapper.map(donorList);

        if (donorResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donorResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Donor> finById(@PathVariable("id") Long id) {
        Optional<Donor> donors = donorService.finByUserId(id);

        if (!donors.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donors.get());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Donor> finByUserId(@RequestParam("userId") Long userId) {
        Optional<Donor> fornecedores = donorService.finByUserId(userId);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<DonorResponse> save(@Valid @RequestBody DonorRequest donorRequest) throws Exception {
        Donor donor = donorMapper.from(donorRequest);
        Donor saved = donorService.save(donor);
        DonorResponse donorResponse = donorMapper.to(saved);

        if (donorResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(donorResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DonorResponse> update(@PathVariable("id") Long id, @Valid @RequestBody DonorRequest donorRequest) {
        Donor donor = donorMapper.from(donorRequest);
        Donor updated = donorService.update(id, donor);
        DonorResponse donorResponse = donorMapper.to(updated);

        if (donorResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(donorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        donorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
