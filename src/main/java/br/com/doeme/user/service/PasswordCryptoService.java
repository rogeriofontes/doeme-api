package br.com.doeme.user.service;

public interface PasswordCryptoService {
    String encrypt(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
