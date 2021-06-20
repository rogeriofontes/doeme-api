package br.com.doeme.usuario.service;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.exceptions.ResourceNotFoundException;
import br.com.doeme.usuario.dto.TokenResponse;
import br.com.doeme.usuario.entiry.Usuario;

public interface UserService {
    TokenResponse getLoginAndReturnToken(Usuario usuario) throws ResourceNotFoundException;
    Usuario register(Usuario usuario) throws ResourceFoundException, ResourceNotFoundException;
}
