package com.blogSearch.exception;

import java.util.function.Supplier;

public class CustomException extends RuntimeException implements Supplier<RuntimeException> {

    public CustomException(String message) {
        super(message);
    }

    @Override
    public RuntimeException get() {
        return this;
    }
}
