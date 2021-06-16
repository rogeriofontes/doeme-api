package br.com.doeme.usuario.v1.resources;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.usuario.dto.TokenResponse;
import br.com.doeme.usuario.entiry.Usuario;
import br.com.doeme.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody Usuario usuario) throws Exception {
        TokenResponse token = userService.getToken(usuario);
        if (token != null) {
           return ResponseEntity.ok(token);
        }

        throw new ResourceFoundException("Usuário não cadastrado!");
    }


}