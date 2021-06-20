package br.com.doeme.usuario.v1.resources;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.exceptions.ResourceNotFoundException;
import br.com.doeme.usuario.dto.UsuarioRequest;
import br.com.doeme.usuario.dto.UsuarioResponse;
import br.com.doeme.usuario.entiry.Usuario;
import br.com.doeme.usuario.mapper.UsuarioMapper;
import br.com.doeme.usuario.repositories.UsuarioRepository;
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

    @Autowired
   private UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseBody
    public ResponseEntity<UsuarioResponse> register(@RequestBody UsuarioRequest usuarioRequest, UriComponentsBuilder uriBuilder) throws ResourceFoundException, ResourceNotFoundException {
        Usuario usuario = usuarioMapper.from(usuarioRequest);
        Usuario registered = userService.register(usuario);
        UsuarioResponse usuarioResponse = usuarioMapper.to(registered);

        if (registered != null){
            URI location = uriBuilder.path("/{id}").buildAndExpand(usuarioResponse.getId()).toUri();
            return ResponseEntity.created(location).body(usuarioResponse);
        }

        throw new ResourceFoundException("Usuário não cadastrado!");
    }


}