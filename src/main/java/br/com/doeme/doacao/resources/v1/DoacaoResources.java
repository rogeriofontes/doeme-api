package br.com.doeme.doacao.resources.v1;

import br.com.doeme.doacao.model.entity.Doacao;
import br.com.doeme.doacao.model.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/doacoes")
public class DoacaoResources {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Doacao>> list() {
        List<Doacao> doacaoList = doacaoService.list();

        if (doacaoList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doacaoList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Doacao> finById(@PathVariable("id") Long id) {
        Optional<Doacao> doacoes = doacaoService.findById(id);

        if (!doacoes.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doacoes.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Doacao> save(@Valid @RequestBody Doacao doacao) throws Exception {
        Doacao saved = doacaoService.save(doacao);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Doacao> update(@PathVariable("id") Long id, @Valid @RequestBody Doacao doacao) {
        Doacao updated = doacaoService.update(id, doacao);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        doacaoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
