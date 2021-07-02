package br.com.doeme.user.service;

import br.com.doeme.user.entiry.User;
import br.com.doeme.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(user -> {
            return criaUserParaAutenticacao(user);
        }).orElse(null);
    }

    private UserDetails criaUserParaAutenticacao(User user) {
        if (!user.getAuthorities().isEmpty()) {
            List<GrantedAuthority> authorities = user.getAuthorities().stream().collect(Collectors.toList());
            log.info("Authorities {}", authorities);
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }

        return null;
    }
}
