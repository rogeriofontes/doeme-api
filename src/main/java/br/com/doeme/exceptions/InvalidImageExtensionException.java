package br.com.doeme.exceptions;

import java.util.List;

public class InvalidImageExtensionException extends Exception  {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidImageExtensionException(final List<String> message) {
        super(String.valueOf(message));
    }
}
