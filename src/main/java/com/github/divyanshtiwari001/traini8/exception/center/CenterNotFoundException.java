package com.github.divyanshtiwari001.traini8.exception.center;

public class CenterNotFoundException extends RuntimeException {
    public CenterNotFoundException(Long id) {
        super("Could not find center " + id);
    }
}
