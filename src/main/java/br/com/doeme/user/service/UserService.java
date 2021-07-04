package br.com.doeme.user.service;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.exceptions.ResourceNotFoundException;
import br.com.doeme.user.dto.RegisterResponse;
import br.com.doeme.user.dto.TokenResponse;
import br.com.doeme.user.entiry.User;

public interface UserService {
    TokenResponse getLoginAndReturnToken(User user) throws ResourceNotFoundException;
    RegisterResponse register(User user) throws ResourceFoundException, ResourceNotFoundException;
}
