package com.onetwo.letterservice.application.port.out;

import com.onetwo.letterservice.domain.Letter;

public interface RegisterLetterPort {
    Letter registerLetter(Letter newLetter);
}
