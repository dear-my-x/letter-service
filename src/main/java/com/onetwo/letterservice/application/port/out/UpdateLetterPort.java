package com.onetwo.letterservice.application.port.out;

import com.onetwo.letterservice.domain.Letter;

public interface UpdateLetterPort {
    void updateLetter(Letter letter);
}
