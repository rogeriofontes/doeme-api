package br.com.doeme.usuario.service;

import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.exceptions.ResourceNotFoundException;
import br.com.doeme.filter.JWTUtil;
import br.com.doeme.usuario.dto.TokenResponse;
import br.com.doeme.usuario.entiry.Usuario;
import br.com.doeme.usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordCryptoService passwordCryptoService;

    @Override
    public TokenResponse getToken(Usuario usuario) throws ResourceNotFoundException {
        Optional<Usuario> usuarioOptional = getUsuarioByEmail(usuario);

        if (usuarioOptional.isPresent()) {
            return TokenResponse.builder()
                    .userId(usuarioOptional.get().getId())
                    .token(JWTUtil.createToken(usuarioOptional.get().getEmail()))
                    .roles(usuarioOptional.get().getPerfils()).build();
        }

        return null;
    }

    @Override
    public Usuario register(Usuario usuario) throws ResourceFoundException {
        if (checkIfUserExist(usuario.getEmail())) {
            throw new ResourceFoundException("Usuario já existe!");
        } else {
            Usuario usuarioMap = Usuario.builder()
                    .name(usuario.getName())
                    .email(usuario.getEmail())
                    .password(getCryptoPassword(usuario.getPassword()))
                    .perfils(usuario.getPerfils())
                    .build();

            return usuarioRepository.save(usuarioMap);
        }
    }

    private Optional<Usuario> getUsuarioByEmail(Usuario usuario) throws ResourceNotFoundException {
        UserDetails userResult = getUserDetails(usuario);
        return getByEmail(userResult.getUsername());
    }

    private UserDetails getUserDetails(Usuario usuario) throws ResourceNotFoundException {
        UserDetails userResult = userDetailsService.loadUserByUsername(usuario.getEmail());
        if (userResult == null) {
            throw new ResourceNotFoundException("Usuário não cadastrado");
        }
        return userResult;
    }

    private boolean checkIfUserExist(String email) {
        return getByEmail(email).isPresent() ? Boolean.TRUE : Boolean.FALSE;
    }

    private Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private String getCryptoPassword(String password) {
        return passwordCryptoService.encrypt(password);
    }

}
