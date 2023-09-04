package com.example.Escriba.application.exceptions;

public class CadastroNotFoundException extends RuntimeException {
    public CadastroNotFoundException(String message) {
        super(message);
    }
}
