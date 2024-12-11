package com.onetwo.letterservice.application.port.out;

import com.onetwo.letterservice.domain.Letter;

import java.util.Optional;

public interface ReadLetterPort {
    Optional<Letter> findById(Long letterId);
}
