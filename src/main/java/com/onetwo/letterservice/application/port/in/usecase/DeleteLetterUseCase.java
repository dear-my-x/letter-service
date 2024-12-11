package com.onetwo.letterservice.application.port.in.usecase;

import com.onetwo.letterservice.application.port.in.command.DeleteLetterCommand;
import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;

public interface DeleteLetterUseCase {

    /**
     * Delete letter use case,
     * delete letter data to persistence
     *
     * @param deleteLetterCommand request delete data about letter's userid, letter's id
     * @return Boolean about delete letter success
     */
    DeleteLetterResponseDto deleteLetter(DeleteLetterCommand deleteLetterCommand);
}
