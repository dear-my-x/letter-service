package com.onetwo.letterservice.application.port.in.usecase;

import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;

public interface RegisterLetterUseCase {

    /**
     * Register letter use case,
     * register letter data to persistence
     *
     * @param registerLetterCommand data about register letter with user id and ReceiverUserId id
     * @return Boolean about register success
     */
    RegisterLetterResponseDto registerLetter(RegisterLetterCommand registerLetterCommand);
}
