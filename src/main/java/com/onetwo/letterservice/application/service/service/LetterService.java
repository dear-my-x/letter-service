package com.onetwo.letterservice.application.service.service;

import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.application.port.in.usecase.RegisterLetterUseCase;
import com.onetwo.letterservice.application.port.out.RegisterLetterPort;
import com.onetwo.letterservice.application.service.converter.LetterUseCaseConverter;
import com.onetwo.letterservice.domain.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LetterService implements RegisterLetterUseCase {

    private final RegisterLetterPort registerLetterPort;
    private final LetterUseCaseConverter letterUseCaseConverter;

    /**
     * Register letter use case,
     * register letter data to persistence
     *
     * @param registerLetterCommand data about register letter with user id and target user id
     * @return Boolean about register success
     */
    @Override
    @Transactional
    public RegisterLetterResponseDto registerLetter(RegisterLetterCommand registerLetterCommand) {
        Letter newLetter = Letter.createNewLetterByCommand(registerLetterCommand);

        Letter savedLetter = registerLetterPort.registerLetter(newLetter);

        return letterUseCaseConverter.letterToRegisterResponseDto(savedLetter);
    }
}
