package br.com.doeme.doeme.pessoa.api.resources.v1;

import br.com.doeme.doeme.exceptions.ResourceFoundException;
import br.com.doeme.doeme.pessoa.model.entity.Pessoa;
import br.com.doeme.doeme.pessoa.model.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaResources {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Pessoa>> list() {
        List<Pessoa> pessoaList = pessoaService.list();

        if (pessoaList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoaList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Pessoa> finById(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);

        if (!pessoa.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa) throws ResourceFoundException {
        Pessoa saved = pessoaService.save(pessoa);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Pessoa> update(@PathVariable("id") Long id, @Valid @RequestBody Pessoa pessoa) {
        Pessoa updated = pessoaService.update(id, pessoa);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        pessoaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
