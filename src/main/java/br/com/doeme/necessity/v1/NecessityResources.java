package br.com.doeme.necessity.v1;

import br.com.doeme.necessity.dto.NecessityMapper;
import br.com.doeme.necessity.dto.NecessityRequest;
import br.com.doeme.necessity.dto.NecessityResponse;
import br.com.doeme.necessity.model.entity.Necessity;
import br.com.doeme.necessity.model.service.NecessityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/necessities")
public class NecessityResources {

    @Autowired
    private NecessityService necessityService;

    @Autowired
    private NecessityMapper necessityMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<NecessityResponse>> list() {
        List<Necessity> necessityList = necessityService.list();
        List<NecessityResponse> necessityResponseLists = necessityMapper.map(necessityList);

        if (necessityResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(necessityResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Necessity> finById(@PathVariable("id") Long id) {
        Optional<Necessity> fornecedores = necessityService.findById(id);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Necessity> finByUserId(@RequestParam("userId") Long userId) {
        Optional<Necessity> grantees = necessityService.findByBeneficiaryId(userId);

        if (!grantees.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(grantees.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<NecessityResponse> save(@Valid @RequestBody NecessityRequest necessityRequest) throws Exception {
        Necessity necessity = necessityMapper.from(necessityRequest);
        Necessity saved = necessityService.save(necessity);
        NecessityResponse necessityResponse = necessityMapper.to(saved);

        if (necessityResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(necessityResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<NecessityResponse> update(@PathVariable("id") Long id, @Valid @RequestBody NecessityRequest necessityRequest) {
        Necessity necessity = necessityMapper.from(necessityRequest);
        Necessity updated = necessityService.update(id, necessity);
        NecessityResponse necessityResponse = necessityMapper.to(updated);

        if (necessityResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(necessityResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        necessityService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
