package br.com.doeme.doeme.fornecedor.v1;

import br.com.doeme.doeme.fornecedor.model.entity.Fornecedor;
import br.com.doeme.doeme.fornecedor.model.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/fornecedoress")
public class FornecedorResources {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Fornecedor>> list() {
        List<Fornecedor> fornecedorList = fornecedorService.list();

        if (fornecedorList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedorList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Fornecedor> finById(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedores = fornecedorService.findById(id);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Fornecedor> save(@Valid @RequestBody Fornecedor fornecedor) throws Exception {
        Fornecedor saved = fornecedorService.save(fornecedor);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Fornecedor> update(@PathVariable("id") Long id, @Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor updated = fornecedorService.update(id, fornecedor);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        fornecedorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
