package com.onetwo.letterservice.common.exceptions;

import com.onetwo.letterservice.common.jwt.JwtCode;
import lombok.Getter;

@Getter
public class TokenValidationException extends RuntimeException {

    public TokenValidationException(JwtCode code) {
        super(code.getValue());
    }
}
