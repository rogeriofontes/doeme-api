package br.com.doeme.user.v1.resources;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.exceptions.ResourceNotFoundException;
import br.com.doeme.user.dto.UserRequest;
import br.com.doeme.user.dto.UserResponse;
import br.com.doeme.user.entiry.User;
import br.com.doeme.user.mapper.UserMapper;
import br.com.doeme.user.repositories.UserRepository;
import br.com.doeme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
   private UserMapper userMapper;

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder) throws ResourceFoundException, ResourceNotFoundException {
        User user = userMapper.from(userRequest);
        User registered = userService.register(user);
        UserResponse userResponse = userMapper.to(registered);

        if (registered != null){
            URI location = uriBuilder.path("/{id}").buildAndExpand(userResponse.getId()).toUri();
            return ResponseEntity.created(location).body(userResponse);
        }

        throw new ResourceFoundException("Usuário não cadastrado!");
    }


}