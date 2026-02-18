package com.appdev.valueobject;

import java.util.regex.Pattern;

public record Email(String address) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email {
        if (address == null || !EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }
}
