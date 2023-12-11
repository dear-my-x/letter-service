package com.onetwo.letterservice.application.service.converter;

import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.domain.Letter;
import org.springframework.stereotype.Component;

@Component
public class LetterUseCaseConverterImpl implements LetterUseCaseConverter {
    @Override
    public RegisterLetterResponseDto letterToRegisterResponseDto(Letter savedLetter) {
        return new RegisterLetterResponseDto(savedLetter != null && savedLetter.getId() != null);
    }
}
