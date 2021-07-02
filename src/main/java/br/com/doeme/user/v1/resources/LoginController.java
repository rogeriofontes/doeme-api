package br.com.doeme.user.v1.resources;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.user.dto.TokenResponse;
import br.com.doeme.user.entiry.User;
import br.com.doeme.user.service.UserService;
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
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody User user) throws Exception {
        TokenResponse token = userService.getLoginAndReturnToken(user);
        if (token != null) {
           return ResponseEntity.ok(token);
        }

        throw new ResourceFoundException("Usuário não cadastrado!");
    }


}