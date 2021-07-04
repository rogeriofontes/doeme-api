package br.com.doeme.ong.v1;

import br.com.doeme.ong.dto.OngMapper;
import br.com.doeme.ong.dto.OngRequest;
import br.com.doeme.ong.dto.OngResponse;
import br.com.doeme.ong.model.entity.Ong;
import br.com.doeme.ong.model.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ongs")
public class OngResources {

    @Autowired
    private OngService ongService;

    @Autowired
    private OngMapper ongMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<OngResponse>> list() {
        List<Ong> beneficiaryList = ongService.list();
        List<OngResponse> ongResponseLists = ongMapper.map(beneficiaryList);

        if (ongResponseLists.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ongResponseLists);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Ong> finById(@PathVariable("id") Long id) {
        Optional<Ong> ongs = ongService.findById(id);

        if (!ongs.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ongs.get());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Ong> finByUserId(@RequestParam("userId") Long userId) {
        Optional<Ong> ongs = ongService.findByUserId(userId);

        if (!ongs.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ongs.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<OngResponse> save(@Valid @RequestBody OngRequest ongRequest) throws Exception {
        Ong ong = ongMapper.from(ongRequest);
        Ong saved = ongService.save(ong);
        OngResponse beneficiaryResponse = ongMapper.to(saved);

        if (beneficiaryResponse == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(beneficiaryResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<OngResponse> update(@PathVariable("id") Long id, @Valid @RequestBody OngRequest ongRequest) {
        Ong ong = ongMapper.from(ongRequest);
        Ong updated = ongService.update(id, ong);
        OngResponse beneficiaryResponse = ongMapper.to(updated);

        if (beneficiaryResponse == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(beneficiaryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        ongService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
