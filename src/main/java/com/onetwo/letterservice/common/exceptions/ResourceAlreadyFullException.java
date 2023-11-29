package com.onetwo.letterservice.common.exceptions;

import lombok.Getter;

@Getter
public class ResourceAlreadyFullException extends RuntimeException {

    public ResourceAlreadyFullException(String message) {
        super(message);
    }
}
