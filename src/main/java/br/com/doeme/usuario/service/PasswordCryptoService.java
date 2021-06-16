package br.com.doeme.usuario.service;

public interface PasswordCryptoService {
    String encrypt(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
