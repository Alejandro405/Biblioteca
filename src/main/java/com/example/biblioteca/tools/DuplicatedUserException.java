package com.example.biblioteca.tools;

import java.security.cert.Extension;

public class DuplicatedUserException extends Exception {
    public DuplicatedUserException(String s) {
        super(s);
    }
}
