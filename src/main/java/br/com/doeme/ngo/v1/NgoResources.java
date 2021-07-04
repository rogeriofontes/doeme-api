package br.com.doeme.ngo.v1;

import br.com.doeme.ngo.dto.NgoMapper;
import br.com.doeme.ngo.dto.NgoRequest;
import br.com.doeme.ngo.dto.NgoResponse;
import br.com.doeme.ngo.model.entity.Ngo;
import br.com.doeme.ngo.model.service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ngos")
public class NgoResources {

    @Autowired
    private NgoService ngoService;

    @Autowired
    private NgoMapper ngoMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<NgoResponse>> list() {
        List<Ngo> beneficiaryList = ngoService.list();
        List<NgoResponse> ngoResponseLists = ngoMapper.map(beneficiaryList);

        if (ngoResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ngoResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Ngo> finById(@PathVariable("id") Long id) {
        Optional<Ngo> ngos = ngoService.findById(id);

        if (!ngos.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ngos.get());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Ngo> finByUserId(@RequestParam("userId") Long userId) {
        Optional<Ngo> ngos = ngoService.findByUserId(userId);

        if (!ngos.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ngos.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<NgoResponse> save(@Valid @RequestBody NgoRequest ngoRequest) throws Exception {
        Ngo ngo = ngoMapper.from(ngoRequest);
        Ngo saved = ngoService.save(ngo);
        NgoResponse beneficiaryResponse = ngoMapper.to(saved);

        if (beneficiaryResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(beneficiaryResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<NgoResponse> update(@PathVariable("id") Long id, @Valid @RequestBody NgoRequest ngoRequest) {
        Ngo ngo = ngoMapper.from(ngoRequest);
        Ngo updated = ngoService.update(id, ngo);
        NgoResponse beneficiaryResponse = ngoMapper.to(updated);

        if (beneficiaryResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(beneficiaryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        ngoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
