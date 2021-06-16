package br.com.doeme.usuario.v1.resources;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.usuario.entiry.Usuario;
import br.com.doeme.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/cadastro")
public class CadastroController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) throws ResourceFoundException {
        Usuario registered = userService.register(usuario);

        if (registered != null){
            URI location = uriBuilder.path("/{id}").buildAndExpand(registered.getId()).toUri();
            return ResponseEntity.created(location).body(registered);
        }

        throw new ResourceFoundException("Usuário não cadastrado!");
    }


}