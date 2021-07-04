package br.com.doeme.beneficiary.v1;

import br.com.doeme.beneficiary.dto.BeneficiaryMapper;
import br.com.doeme.beneficiary.dto.BeneficiaryRequest;
import br.com.doeme.beneficiary.dto.BeneficiaryResponse;
import br.com.doeme.beneficiary.model.entity.Beneficiary;
import br.com.doeme.beneficiary.model.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/beneficiaries")
public class BeneficiaryResources {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<BeneficiaryResponse>> list() {
        List<Beneficiary> beneficiaryList = beneficiaryService.list();
        List<BeneficiaryResponse> beneficiaryResponseLists = beneficiaryMapper.map(beneficiaryList);

        if (beneficiaryResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(beneficiaryResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Beneficiary> finById(@PathVariable("id") Long id) {
        Optional<Beneficiary> fornecedores = beneficiaryService.findById(id);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Beneficiary> finByUserId(@RequestParam("userId") Long userId) {
        Optional<Beneficiary> fornecedores = beneficiaryService.findByUserId(userId);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BeneficiaryResponse> save(@Valid @RequestBody BeneficiaryRequest beneficiaryRequest) throws Exception {
        Beneficiary beneficiary = beneficiaryMapper.from(beneficiaryRequest);
        Beneficiary saved = beneficiaryService.save(beneficiary);
        BeneficiaryResponse beneficiaryResponse = beneficiaryMapper.to(saved);

        if (beneficiaryResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(beneficiaryResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BeneficiaryResponse> update(@PathVariable("id") Long id, @Valid @RequestBody BeneficiaryRequest beneficiaryRequest) {
        Beneficiary beneficiary = beneficiaryMapper.from(beneficiaryRequest);
        Beneficiary updated = beneficiaryService.update(id, beneficiary);
        BeneficiaryResponse beneficiaryResponse = beneficiaryMapper.to(updated);

        if (beneficiaryResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(beneficiaryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        beneficiaryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
