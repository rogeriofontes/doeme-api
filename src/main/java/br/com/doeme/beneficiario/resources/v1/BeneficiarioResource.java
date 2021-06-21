package br.com.doeme.beneficiario.resources.v1;

import br.com.doeme.beneficiario.model.entity.Beneficiario;
import br.com.doeme.beneficiario.model.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/beneficiarios")
public class BeneficiarioResource {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Beneficiario>> list() {
        List<Beneficiario> beneficiariosList = beneficiarioService.list();

        if (beneficiariosList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(beneficiariosList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Beneficiario> finById(@PathVariable("id") Long id) {
        Optional<Beneficiario> beneficiariosList = beneficiarioService.findById(id);

        if (!beneficiariosList.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(beneficiariosList.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Beneficiario> save(@Valid @RequestBody Beneficiario doador) throws Exception {
        Beneficiario saved = beneficiarioService.save(doador);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Beneficiario> update(@PathVariable("id") Long id, @Valid @RequestBody Beneficiario beneficiario) {
        Beneficiario updated = beneficiarioService.update(id, beneficiario);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        beneficiarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
