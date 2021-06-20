package br.com.doeme.fornecedor.v1;

import br.com.doeme.fornecedor.dto.FornecedorMapper;
import br.com.doeme.fornecedor.dto.FornecedorRequest;
import br.com.doeme.fornecedor.dto.FornecedorResponse;
import br.com.doeme.fornecedor.model.entity.Fornecedor;
import br.com.doeme.fornecedor.model.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/fornecedores")
public class FornecedorResources {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private FornecedorMapper fornecedorMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<FornecedorResponse>> list() {
        List<Fornecedor> fornecedorList = fornecedorService.list();
        List<FornecedorResponse> fornecedorResponses = fornecedorMapper.map(fornecedorList);

        if (fornecedorResponses.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedorResponses);
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
    public ResponseEntity<FornecedorResponse> save(@Valid @RequestBody FornecedorRequest fornecedorRequest) throws Exception {
        Fornecedor fornecedor = fornecedorMapper.from(fornecedorRequest);
        Fornecedor saved = fornecedorService.save(fornecedor);
        FornecedorResponse fornecedorResponse = fornecedorMapper.to(saved);

        if (fornecedorResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(fornecedorResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FornecedorResponse> update(@PathVariable("id") Long id, @Valid @RequestBody FornecedorRequest fornecedorRequest) {
        Fornecedor fornecedor = fornecedorMapper.from(fornecedorRequest);
        Fornecedor updated = fornecedorService.update(id, fornecedor);
        FornecedorResponse fornecedorResponse = fornecedorMapper.to(updated);

        if (fornecedorResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        fornecedorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
