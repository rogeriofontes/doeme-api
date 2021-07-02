package br.com.doeme.grantee.v1;

import br.com.doeme.grantee.dto.GranteeMapper;
import br.com.doeme.grantee.dto.GranteeRequest;
import br.com.doeme.grantee.dto.GranteeResponse;
import br.com.doeme.grantee.model.entity.Grantee;
import br.com.doeme.grantee.model.service.GranteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/grantees")
public class GranteeResources {

    @Autowired
    private GranteeService granteeService;

    @Autowired
    private GranteeMapper granteeMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<GranteeResponse>> list() {
        List<Grantee> granteeList = granteeService.list();
        List<GranteeResponse> granteeResponseLists = granteeMapper.map(granteeList);

        if (granteeResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(granteeResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Grantee> finById(@PathVariable("id") Long id) {
        Optional<Grantee> fornecedores = granteeService.findById(id);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Grantee> finByUserId(@RequestParam("userId") Long userId) {
        Optional<Grantee> fornecedores = granteeService.findByUserId(userId);

        if (!fornecedores.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fornecedores.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<GranteeResponse> save(@Valid @RequestBody GranteeRequest granteeRequest) throws Exception {
        Grantee grantee = granteeMapper.from(granteeRequest);
        Grantee saved = granteeService.save(grantee);
        GranteeResponse granteeResponse = granteeMapper.to(saved);

        if (granteeResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(granteeResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<GranteeResponse> update(@PathVariable("id") Long id, @Valid @RequestBody GranteeRequest granteeRequest) {
        Grantee grantee = granteeMapper.from(granteeRequest);
        Grantee updated = granteeService.update(id, grantee);
        GranteeResponse granteeResponse = granteeMapper.to(updated);

        if (granteeResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(granteeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        granteeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
