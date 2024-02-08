package com.onetwo.letterservice.common.exceptions;

import lombok.Getter;

@Getter
public class ResourceAlreadyDeletedException extends RuntimeException {

    public ResourceAlreadyDeletedException(String message) {
        super(message);
    }
}