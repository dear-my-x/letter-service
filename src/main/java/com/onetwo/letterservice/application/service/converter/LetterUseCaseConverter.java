package com.onetwo.letterservice.application.service.converter;

import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.domain.Letter;

public interface LetterUseCaseConverter {
    RegisterLetterResponseDto letterToRegisterResponseDto(Letter savedLetter);

    DeleteLetterResponseDto letterToDeleteResponseDto(boolean result);
}
